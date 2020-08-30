
package com.yvanscoop.beignetShop.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin,Accept,X-Request-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin,Access-Control-Allow-Credentials,Authorization");
		response.addHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,PATCH");

		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else if (request.getRequestURI().equals("/login")) {
			filterChain.doFilter(request, response);
		} else {
			String jwtToken = request.getHeader(SecurityConstants.HEADER_STRING);
			
			if (jwtToken == null || !jwtToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}

			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(jwtToken.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody();
			String username = claims.getSubject();
			
			@SuppressWarnings("unchecked")
			List<String> roles = (List<String>) claims.get("roles");

			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(r -> {
				authorities.add(new SimpleGrantedAuthority(r));
			});

			UsernamePasswordAuthenticationToken authenticatedUser = new UsernamePasswordAuthenticationToken(username,
					null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			filterChain.doFilter(request, response);
		}

	}
}
