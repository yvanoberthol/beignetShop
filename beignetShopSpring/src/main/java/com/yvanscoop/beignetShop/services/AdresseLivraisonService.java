package com.yvanscoop.beignetShop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yvanscoop.beignetShop.entities.AdresseLivraison;
import com.yvanscoop.beignetShop.entities.Utilisateur;
import com.yvanscoop.beignetShop.repositories.AdresseLivraisonRepository;
import com.yvanscoop.beignetShop.services.interfaces.ServiceInterface;

@Service
public class AdresseLivraisonService implements ServiceInterface<AdresseLivraison>{
	
	@Autowired
	private AdresseLivraisonRepository adresseLivraisonRepository;

	@Override
	public AdresseLivraison create(AdresseLivraison adresseLivraison) {
		// TODO Auto-generated method stub
		return adresseLivraisonRepository.save(adresseLivraison);
	}

	@Override
	public List<AdresseLivraison> getAll() {
		// TODO Auto-generated method stub
		return adresseLivraisonRepository.findAll();
	}

	@Override
	public AdresseLivraison getOne(Long id) {
		// TODO Auto-generated method stub
		return adresseLivraisonRepository.getOne(id);
	}

	@Override
	public AdresseLivraison update(AdresseLivraison adresseLivraison) {
		// TODO Auto-generated method stub
		return adresseLivraisonRepository.saveAndFlush(adresseLivraison);
	}

	@Override
	public void delete(AdresseLivraison adresseLivraison) {
		// TODO Auto-generated method stub
		adresseLivraisonRepository.delete(adresseLivraison);
		
	}
	
	public List<AdresseLivraison> getAllByUtilisateur(Utilisateur utilisateur) {
		return adresseLivraisonRepository.findByUtilisateur(utilisateur);
	}
	
	public AdresseLivraison findByQuartier(String quartier, Utilisateur utilisateur) {
		return adresseLivraisonRepository.findByQuartierAndUtilisateur(quartier, utilisateur);
	}

}
