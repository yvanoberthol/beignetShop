package com.yvanscoop.beignetShop.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yvanscoop.beignetShop.entities.Panier;
import com.yvanscoop.beignetShop.entities.Utilisateur;
import com.yvanscoop.beignetShop.repositories.PanierRepository;
import com.yvanscoop.beignetShop.services.interfaces.ServiceInterface;


@Service
@Transactional
public class PanierService implements ServiceInterface<Panier>{

	
	@Autowired
	private PanierRepository panierRepository;
	
	@Override
	public Panier create(Panier panier) {
		// TODO Auto-generated method stub
		return panierRepository.save(panier);
	}

	@Override
	public List<Panier> getAll() {
		// TODO Auto-generated method stub
		return panierRepository.findAll();
	}

	@Override
	public Panier getOne(Long id) {
		// TODO Auto-generated method stub
		return panierRepository.getOne(id);
	}
	
	public Panier getByUtilisateur(Utilisateur utilisateur){
		return panierRepository.findByUtilisateur(utilisateur);
	}

	@Override
	public Panier update(Panier panier) {
		// TODO Auto-generated method stub
		return panierRepository.saveAndFlush(panier);
	}

	@Override
	public void delete(Panier panier) {
		// TODO Auto-generated method stub
		panierRepository.delete(panier);
	}

}
