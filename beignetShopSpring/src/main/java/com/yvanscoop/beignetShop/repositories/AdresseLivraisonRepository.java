package com.yvanscoop.beignetShop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yvanscoop.beignetShop.entities.AdresseLivraison;
import com.yvanscoop.beignetShop.entities.Utilisateur;

@Repository
public interface AdresseLivraisonRepository extends JpaRepository<AdresseLivraison, Long>{
	
	AdresseLivraison findByQuartierAndUtilisateur(String quartier,Utilisateur utilisateur);
	
	List<AdresseLivraison> findByUtilisateur(Utilisateur utilisateur);

}
