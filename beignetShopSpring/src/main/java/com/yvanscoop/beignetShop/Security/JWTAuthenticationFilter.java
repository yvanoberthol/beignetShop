
package com.yvanscoop.beignetShop.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yvanscoop.beignetShop.entities.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@SneakyThrows
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		Utilisateur utilisateur = null;
		try {
			utilisateur = new ObjectMapper().readValue(request.getInputStream(), Utilisateur.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(utilisateur.getUsername(), utilisateur.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		Utilisateur springUser = (Utilisateur) authResult.getPrincipal();
		List<String> roles = new ArrayList<>();
		springUser.getAuthorities().forEach(role -> {
			roles.add(role.getAuthority());
		});
		String jwtToken = Jwts.builder().setIssuer(request.getRequestURI()).setSubject(springUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.claim("id", springUser.getId())
				.claim("username", springUser.getNomComplet())
				.claim("roles", roles).compact();
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwtToken);

	}
}
