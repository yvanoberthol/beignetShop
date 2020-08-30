package com.yvanscoop.beignetShop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yvanscoop.beignetShop.entities.Commande;
import com.yvanscoop.beignetShop.entities.Utilisateur;
@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long>{
	
	@Query("select c from Commande c where c.panier.utilisateur = :utilisateur")
	List<Commande> findByUtilisateur(@Param(value ="utilisateur") Utilisateur utilisateur);

}
