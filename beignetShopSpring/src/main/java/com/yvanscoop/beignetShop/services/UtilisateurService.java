package com.yvanscoop.beignetShop.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yvanscoop.beignetShop.entities.Utilisateur;
import com.yvanscoop.beignetShop.repositories.UtilisateurRepository;
import com.yvanscoop.beignetShop.services.interfaces.ServiceInterface;


@Service
@Transactional
public class UtilisateurService implements ServiceInterface<Utilisateur>{
	
	@Autowired
	private UtilisateurRepository userRepository;

	@Override
	public Utilisateur create(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return userRepository.save(utilisateur);
	}

	@Override
	public List<Utilisateur> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Utilisateur getOne(Long id) {
		// TODO Auto-generated method stub
		return userRepository.getOne(id);
	}

	@Override
	public Utilisateur update(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return userRepository.saveAndFlush(utilisateur);
	}

	@Override
	public void delete(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		userRepository.delete(utilisateur);
		
	}
	
	public Utilisateur getByLogin(String login) {
		return userRepository.findByUsername(login);
	}

}
