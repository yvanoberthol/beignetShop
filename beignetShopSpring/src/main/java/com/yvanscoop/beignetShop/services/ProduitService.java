package com.yvanscoop.beignetShop.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yvanscoop.beignetShop.entities.Produit;
import com.yvanscoop.beignetShop.repositories.ProduitRepository;
import com.yvanscoop.beignetShop.services.interfaces.ServiceInterface;


@Service
@Transactional
public class ProduitService implements ServiceInterface<Produit>{
	
	@Autowired
	private ProduitRepository produitRepository;

	@Override
	public Produit create(Produit produit) {
		// TODO Auto-generated method stub
		return produitRepository.save(produit);
	}

	@Override
	public List<Produit> getAll() {
		// TODO Auto-generated method stub
		return produitRepository.findAll();
	}

	@Override
	public Produit getOne(Long id) {
		// TODO Auto-generated method stub
		return produitRepository.getOne(id);
	}

	@Override
	public Produit update(Produit produit) {
		// TODO Auto-generated method stub
		return produitRepository.saveAndFlush(produit);
	}

	@Override
	public void delete(Produit produit) {
		// TODO Auto-generated method stub
		produitRepository.delete(produit);
	}
	
	public Produit getByName(String libelle) {
		return produitRepository.findByLibelle(libelle);
	}

}
