package com.yvanscoop.beignetShop.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yvanscoop.beignetShop.Response.Response;
import com.yvanscoop.beignetShop.dto.AdresseLivraisonDto;
import com.yvanscoop.beignetShop.entities.AdresseLivraison;
import com.yvanscoop.beignetShop.entities.Utilisateur;
import com.yvanscoop.beignetShop.services.AdresseLivraisonService;
import com.yvanscoop.beignetShop.services.UtilisateurService;

@RestController
@RequestMapping(value = "/api")
public class AdresseLivraisonController {
	
	@Autowired
	private AdresseLivraisonService adresseLivraisonService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping("/adresseLivraison/utilisateur/{utilisateur_id}")
	public ResponseEntity<List<AdresseLivraison>> getByUtilisateur(@PathVariable Long utilisateur_id){
		
		Utilisateur utilisateur = utilisateurService.getOne(utilisateur_id);
		
		if (utilisateur == null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		List<AdresseLivraison> adresseLivraisons = adresseLivraisonService.getAllByUtilisateur(utilisateur);
		
		if (adresseLivraisons.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(adresseLivraisons, HttpStatus.OK);
		
	}
	
	@PostMapping("/adresseLivraison/add")
	public ResponseEntity<Response> addAdresseLivraison(@RequestBody AdresseLivraisonDto adresseLivraisonDto){
		
		Response response = new Response();
		
		Utilisateur utilisateur = utilisateurService.getOne(adresseLivraisonDto.getUtilisateurId());
		if (utilisateur == null) {
			response.setMessageStatus(true);
			response.setMessageString("Le utilisateur n'existe pas");
			return new ResponseEntity<Response>(HttpStatus.CONFLICT);
		}
		
		AdresseLivraison adresseLivraisonByQuartier = adresseLivraisonService.findByQuartier(adresseLivraisonDto.getQuartier(), utilisateur);
		if (adresseLivraisonByQuartier != null) {
			response.setMessageStatus(true);
			response.setMessageString("Ce quartier existe déjà");
			return new ResponseEntity<Response>(HttpStatus.CONFLICT);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		AdresseLivraison adresseLivraison = modelMapper.map(adresseLivraisonDto, AdresseLivraison.class);
		
		adresseLivraison.setUtilisateur(utilisateur);
		
		adresseLivraisonService.create(adresseLivraison);
		
		return  new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/adresseLivraison/delete/{id}")
	public ResponseEntity<Response> deletePlat(@PathVariable Long id) {
		Response response = new Response();
		
		AdresseLivraison adresseLivraison = adresseLivraisonService.getOne(id);
		
		if (adresseLivraison == null) {
			response.setMessageStatus(true);
			response.setMessageString("adressetNotExists");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		adresseLivraisonService.delete(adresseLivraison);
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		
	}

}
