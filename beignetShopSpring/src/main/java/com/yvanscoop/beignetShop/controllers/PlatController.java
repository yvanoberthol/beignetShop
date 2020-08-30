package com.yvanscoop.beignetShop.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yvanscoop.beignetShop.Response.Response;
import com.yvanscoop.beignetShop.dto.PlatDto;
import com.yvanscoop.beignetShop.entities.LignePlat;
import com.yvanscoop.beignetShop.entities.Panier;
import com.yvanscoop.beignetShop.entities.Plat;
import com.yvanscoop.beignetShop.entities.Produit;
import com.yvanscoop.beignetShop.services.LignePlatService;
import com.yvanscoop.beignetShop.services.PanierService;
import com.yvanscoop.beignetShop.services.PlatService;
import com.yvanscoop.beignetShop.services.ProduitService;
import com.yvanscoop.beignetShop.utility.SecurityUtility;

@RestController
@RequestMapping(value = "/api")
public class PlatController {
	@Autowired
	private PlatService platService;
	
	@Autowired
	private PanierService panierService;
	
	@Autowired
	private ProduitService produitService;
	
	@Autowired
	private LignePlatService lignePlatService;
	
	@GetMapping("/plats")
	public ResponseEntity<List<Plat>> getPlats() {
		List<Plat> plats = platService.getAll();
		
		if (plats.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(plats, HttpStatus.OK);
	}
	
	
	@GetMapping("/plat/get/{id}")
	public ResponseEntity<Plat> getPlat(@PathVariable Long id) {
		Plat plat = platService.getOne(id);
		
		if (plat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(plat, HttpStatus.OK);
	}
	
	@PutMapping("/plat/update")
	public ResponseEntity<Response> updatePlat(@Valid @RequestBody final PlatDto platDto) {
		Response response = new Response();
		
		Plat platToUpdate = platService.getOne(platDto.getId());
		platService.update(platToUpdate);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/plat/add")
	@Transactional
	public ResponseEntity<Response> addPlat(@Valid @RequestBody final PlatDto platDto) {
		Response response = new Response();
		
		Panier panier = panierService.getOne(platDto.getPanier_id());
		
		if (panier == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Plat plat = new Plat();
		plat.setCode(SecurityUtility.randomToken());
		plat.setMontant(platDto.getMontant());
		plat.setPanier(panier);
		Plat platCreatedPlat = platService.create(plat);
		
		if (!platDto.getLignePlats().isEmpty()) {
			platDto.getLignePlats().forEach(lignePlatDto -> {
				Produit produit = produitService.getByName(lignePlatDto.getNomProduit());
				LignePlat lignePlat = new LignePlat();
				lignePlat.setProduit(produit);
				lignePlat.setPlat(platCreatedPlat);
				lignePlat.setMontant(lignePlatDto.getMontant());
				lignePlatService.create(lignePlat);
			});
		
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/plat/delete/{id}")
	public ResponseEntity<Response> deletePlat(@PathVariable Long id) {
		Response response = new Response();
		
		Plat plat = platService.getOne(id);
		
		if (plat == null) {
			response.setMessageStatus(true);
			response.setMessageString("platNotExists");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		platService.delete(plat);
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		
	}
	
}
