package com.yvanscoop.beignetShop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yvanscoop.beignetShop.Response.Response;
import com.yvanscoop.beignetShop.dto.CommandeDto;
import com.yvanscoop.beignetShop.entities.AdresseLivraison;
import com.yvanscoop.beignetShop.entities.Commande;
import com.yvanscoop.beignetShop.entities.Panier;
import com.yvanscoop.beignetShop.entities.Utilisateur;
import com.yvanscoop.beignetShop.services.AdresseLivraisonService;
import com.yvanscoop.beignetShop.services.CommandeService;
import com.yvanscoop.beignetShop.services.PanierService;
import com.yvanscoop.beignetShop.services.UtilisateurService;
import com.yvanscoop.beignetShop.utility.SecurityUtility;

@RestController
@RequestMapping(value = "/api")
public class CommandeController {
	
	@Autowired
	private CommandeService commandeService;
	
	@Autowired
	private PanierService panierService;
	
	@Autowired
	private AdresseLivraisonService adresseLivraisonService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping("/commande/utilisateur/{utilisateur_id}")
	public ResponseEntity<List<Commande>> getByUtilisateur(@PathVariable Long utilisateur_id){
		
		Utilisateur utilisateur = utilisateurService.getOne(utilisateur_id);
		
		if (utilisateur == null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		List<Commande> commandes = commandeService.getAll(utilisateur);
		
		if (commandes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(commandes, HttpStatus.OK);
		
	}
	
	@PostMapping("/commande/add")
	@Transactional
	public ResponseEntity<Response> addCommande(@RequestBody CommandeDto commandeDto){
		
		Response response = new Response();
		
		Panier panier = panierService.getOne(commandeDto.getPanierId());
		if (panier == null) {
			response.setMessageStatus(true);
			response.setMessageString("Le panier n'existe pas");
			return new ResponseEntity<Response>(HttpStatus.CONFLICT);
		}
		
		AdresseLivraison adresseLivraison = adresseLivraisonService.getOne(commandeDto.getAdresseLivraisonId());
		if (adresseLivraison == null) {
			response.setMessageStatus(true);
			response.setMessageString("Cette adresse n'existe pas");
			return new ResponseEntity<Response>(HttpStatus.CONFLICT);
		}
		
		panier.setConfirmated(true);
		panierService.update(panier);
		
		Commande commande = new Commande();
		commande.setCode(SecurityUtility.randomToken());
		commande.setPanier(panier);
		commande.setAdresseLivraison(adresseLivraison);
		commandeService.create(commande);
		
		return  new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}

}
