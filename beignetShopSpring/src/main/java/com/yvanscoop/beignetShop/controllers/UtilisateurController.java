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
import com.yvanscoop.beignetShop.dto.UtilisateurDto;
import com.yvanscoop.beignetShop.dto.UtilisateurStatusDto;
import com.yvanscoop.beignetShop.entities.Utilisateur;
import com.yvanscoop.beignetShop.services.UtilisateurService;
import com.yvanscoop.beignetShop.utility.SecurityUtility;

@RestController
@RequestMapping(value = "/api")
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping("/utilisateurs")
	public ResponseEntity<List<Utilisateur>> getUtilisateurs() {
		List<Utilisateur> utilisateurs = utilisateurService.getAll();
		
		if (utilisateurs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
	}
	
	@GetMapping("/utilisateur/get/{id}")
	public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
		Utilisateur utilisateur = utilisateurService.getOne(id);
		
		if (utilisateur == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(utilisateur, HttpStatus.OK);
	}
	
	
	@PutMapping("/utilisateur/update")
	public ResponseEntity<Response> updateUtilisateur(@Valid @RequestBody final UtilisateurDto utilisateurDto) {
		Response response = new Response();
		
		Utilisateur utilisateurToUpdate = utilisateurService.getOne(utilisateurDto.getId());
		if (utilisateurToUpdate == null) {
			response.setMessageStatus(true);
			response.setMessageString("utilisateurNotExists");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		Utilisateur utilisateurByLogin = utilisateurService.getByLogin(utilisateurDto.getUsername());
		if (!utilisateurDto.getId().equals(utilisateurByLogin.getId())) {
			response.setMessageStatus(true);
			response.setMessageString("utilisateurLoginExists");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		Utilisateur utilisateur = modelMapper.map(utilisateurDto, Utilisateur.class);
		utilisateur.setPassword(SecurityUtility.passwordEncoder().encode(utilisateurDto.getPassword()));
		
		utilisateurService.update(utilisateur);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@PutMapping("/utilisateur/updateStatus")
	public ResponseEntity<Response> updateStatusUtilisateur(@Valid @RequestBody final UtilisateurStatusDto utilisateurStatusDto) {
		Response response = new Response();
		
		Utilisateur utilisateurToUpdate = utilisateurService.getOne(utilisateurStatusDto.getId());
		if (utilisateurToUpdate == null) {
			response.setMessageStatus(true);
			response.setMessageString("utilisateurNotExists");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		utilisateurToUpdate.setActive(utilisateurStatusDto.isActive());
		utilisateurService.update(utilisateurToUpdate);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@PostMapping("/utilisateur/add")
	public ResponseEntity<Response> addUtilisateur(@Valid @RequestBody final UtilisateurDto utilisateurDto) {
		Response response = new Response();
		
		Utilisateur utilisateurByLogin = utilisateurService.getByLogin(utilisateurDto.getUsername());
		
		if (utilisateurByLogin != null) {
			response.setMessageStatus(true);
			response.setMessageString("utilisateurLoginExists");
			return new ResponseEntity<>(response,HttpStatus.CONFLICT);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		Utilisateur utilisateur = modelMapper.map(utilisateurDto, Utilisateur.class);
		utilisateur.setPassword(SecurityUtility.passwordEncoder().encode(utilisateurDto.getPassword()));
		
		utilisateurService.create(utilisateur);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/utilisateur/delete/{id}")
	public ResponseEntity<Response> deleteUtilisateur(@PathVariable Long id) {
		Response response = new Response();
		
		Utilisateur utilisateur = utilisateurService.getOne(id);
		
		if (utilisateur == null) {
			response.setMessageStatus(true);
			response.setMessageString("utilisateurNotExists");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		
		utilisateurService.delete(utilisateur);
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		
	}
	
	
}
