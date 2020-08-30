package com.yvanscoop.beignetShop.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdresseLivraisonDto {
	
	private Long id;
	
	@NotNull
	private String quartier;
	private int numBlock;
	private int numCase;
	private Long utilisateurId;

}
