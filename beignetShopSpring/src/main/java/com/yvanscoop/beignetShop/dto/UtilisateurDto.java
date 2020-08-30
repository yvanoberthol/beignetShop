package com.yvanscoop.beignetShop.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class UtilisateurDto {
	
	private Long id;
	
	@NotEmpty(message = "error.utilisateur.nomComplet.notEmpty")
	private String nomComplet;
	
	private int phoneNumber;
	
	@NotEmpty(message = "error.utilisateur.email.notEmpty")
	@Email(message = "error.utilisateur.email.valid")
	private String email;
	
	@NotEmpty(message = "error.utilisateur.login.notEmpty")
	@Length(min = 4, max = 30, message = "error.utilisateur.login.length")
	private String username;
	
	@NotEmpty(message = "error.utilisateur.password.notEmpty")
	@Length(min = 6, message = "error.utilisateur.password.length")
	private String password;
	private String role;
	private boolean active;
}
