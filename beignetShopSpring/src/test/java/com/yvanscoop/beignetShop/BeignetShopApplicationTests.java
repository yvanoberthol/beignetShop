package com.yvanscoop.beignetShop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yvanscoop.beignetShop.entities.Panier;
import com.yvanscoop.beignetShop.entities.Utilisateur;
import com.yvanscoop.beignetShop.repositories.UtilisateurRepository;
import com.yvanscoop.beignetShop.services.PanierService;

@SpringBootTest
class BeignetShopApplicationTests {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private PanierService panierService;

	@Test
	void contextLoads() {
		searchPanier();
	}
	
	void searchUser() {
		Utilisateur utilisateur = utilisateurRepository.findByUsername("Berthold");
		if (utilisateur != null) {
			System.out.print(utilisateur.getEmail()+" "+utilisateur.getRole());
		}
	}
	
	void searchPanier() {
		Utilisateur utilisateur = utilisateurRepository.getOne(1L);
		Panier panier = panierService.getByUtilisateur(utilisateur);
		System.out.println(panier.getCode());
	}

}
