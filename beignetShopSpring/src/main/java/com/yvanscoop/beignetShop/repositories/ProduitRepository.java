package com.yvanscoop.beignetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yvanscoop.beignetShop.entities.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long>{
	Produit findByLibelle(String libelle);
}
