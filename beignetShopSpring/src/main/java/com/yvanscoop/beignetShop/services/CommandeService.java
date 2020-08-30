package com.yvanscoop.beignetShop.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yvanscoop.beignetShop.entities.Commande;
import com.yvanscoop.beignetShop.entities.Utilisateur;
import com.yvanscoop.beignetShop.repositories.CommandeRepository;
import com.yvanscoop.beignetShop.services.interfaces.ServiceInterface;


@Service
@Transactional
public class CommandeService implements ServiceInterface<Commande>{
	
	@Autowired
	private CommandeRepository commandeRepository;

	@Override
	public Commande create(Commande commande) {
		// TODO Auto-generated method stub
		return commandeRepository.save(commande);
	}

	public List<Commande> getAll(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return commandeRepository.findByUtilisateur(utilisateur);
	}

	@Override
	public Commande getOne(Long id) {
		// TODO Auto-generated method stub
		return commandeRepository.getOne(id);
	}

	@Override
	public Commande update(Commande commande) {
		// TODO Auto-generated method stub
		return commandeRepository.saveAndFlush(commande);
	}

	@Override
	public void delete(Commande commande) {
		// TODO Auto-generated method stub
		commandeRepository.delete(commande);
	}

	@Override
	public List<Commande> getAll() {
		// TODO Auto-generated method stub
		return commandeRepository.findAll();
	}

}
