
package com.yvanscoop.beignetShop.services.security;

import com.yvanscoop.beignetShop.entities.Utilisateur;
import com.yvanscoop.beignetShop.repositories.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CompteSecurityService implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
		final Utilisateur utilisateur = utilisateurRepository.findByUsername(login);

		if (null == utilisateur) {
			throw new UsernameNotFoundException("Login or Email not found");
		}
		return utilisateur;
	}
}
