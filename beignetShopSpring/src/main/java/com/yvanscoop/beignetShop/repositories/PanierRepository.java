package com.yvanscoop.beignetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yvanscoop.beignetShop.entities.Panier;
import com.yvanscoop.beignetShop.entities.Utilisateur;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long>{
	
	@Query("select p from Panier p where p.confirmated = false and p.utilisateur = :utilisateur")
	Panier findByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);

}
