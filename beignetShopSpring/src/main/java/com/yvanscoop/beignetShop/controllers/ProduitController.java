package com.yvanscoop.beignetShop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import com.yvanscoop.beignetShop.dto.ProduitDto;
import com.yvanscoop.beignetShop.entities.Produit;
import com.yvanscoop.beignetShop.services.ProduitService;

@RestController
@RequestMapping(value = "/api")
public class ProduitController {
	@Autowired
	private ProduitService produitService;
	
	@GetMapping("/produits")
	public ResponseEntity<List<Produit>> getProduits() {
		List<Produit> produits = produitService.getAll();
		
		if (produits.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(produits, HttpStatus.OK);
	}
	
	
	@GetMapping("/produit/get/{id}")
	public ResponseEntity<Produit> getProduit(@PathVariable Long id) {
		Produit produit = produitService.getOne(id);
		
		if (produit == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(produit, HttpStatus.OK);
	}
	
	@PutMapping("/produit/update")
	public ResponseEntity<Response> updateProduit(@Valid @RequestBody final ProduitDto produitDto) {
		Response response = new Response();
		
		Produit produitToUpdate = produitService.getOne(produitDto.getId());
		
		if (produitToUpdate == null) {
			response.setMessageStatus(true);
			response.setMessageString("produitNotExists");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		
		Produit produitByNameProduit = produitService.getByName(produitDto.getLibelle());
		if (produitByNameProduit.getLibelle().equals(produitDto.getLibelle())) {
			if (!produitByNameProduit.getId().equals(produitDto.getId())) {
				response.setMessageStatus(true);
				response.setMessageString("produitNameExists");
				return new ResponseEntity<>(response,HttpStatus.CONFLICT);
			}
		}
		
		ModelMapper modelMapper = new ModelMapper();
		Produit produit = modelMapper.map(produitDto, Produit.class);
		
		produitService.update(produit);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/produit/add")
	public ResponseEntity<Response> addProduit(@Valid @RequestBody final ProduitDto produitDto) {
		Response response = new Response();
		
		Produit produitByNameProduit = produitService.getByName(produitDto.getLibelle());
		
		if (produitByNameProduit != null) {
			response.setMessageStatus(true);
			response.setMessageString("produitNameExists");
			return new ResponseEntity<>(response,HttpStatus.CONFLICT);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		Produit produit = modelMapper.map(produitDto, Produit.class);
		
		produitService.create(produit);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/produit/delete/{id}")
	public ResponseEntity<Response> deleteProduit(@PathVariable Long id) {
		Response response = new Response();
		
		Produit produit = produitService.getOne(id);
		
		if (produit == null) {
			response.setMessageStatus(true);
			response.setMessageString("produitNotExists");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		produitService.delete(produit);
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		
	}
}
