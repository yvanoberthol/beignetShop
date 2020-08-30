package com.yvanscoop.beignetShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yvanscoop.beignetShop.Response.Response;
import com.yvanscoop.beignetShop.dto.PanierDto;
import com.yvanscoop.beignetShop.entities.Utilisateur;
import com.yvanscoop.beignetShop.entities.Panier;
import com.yvanscoop.beignetShop.services.UtilisateurService;
import com.yvanscoop.beignetShop.services.PanierService;
import com.yvanscoop.beignetShop.utility.SecurityUtility;

@RestController
@RequestMapping(value = "/api")
public class PanierController {
	
	@Autowired
	private PanierService panierService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping("/panier/utilisateur/{utilisateurId}")
	public ResponseEntity<Panier> getByUtilisateur(@PathVariable Long utilisateurId){
		
		Utilisateur utilisateur = utilisateurService.getOne(utilisateurId);

		if (utilisateur == null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		Panier panier = panierService.getByUtilisateur(utilisateur);
		
		if (panier == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(panier, HttpStatus.OK);
		
	}
	
	@PostMapping("/panier/add")
	public ResponseEntity<Response> addPanier(@RequestBody PanierDto panierDto){
		
		Response response = new Response();
		
		Utilisateur utilisateur = utilisateurService.getOne(panierDto.getUtilisateurId());

		if (utilisateur == null) {
			response.setMessageStatus(true);
			response.setMessageString("L' utilisateur n'existe pas");
			return new ResponseEntity<>(response,HttpStatus.CONFLICT);
		}

		Panier panierNotConfirmated = panierService.getByUtilisateur(utilisateur);

		if (panierNotConfirmated == null){
			Panier panier = new Panier();
			panier.setCode(SecurityUtility.randomToken());
			panier.setUtilisateur(utilisateur);
			panierService.create(panier);
		}

		
		return  new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}

}
