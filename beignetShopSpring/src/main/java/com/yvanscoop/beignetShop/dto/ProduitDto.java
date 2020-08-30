package com.yvanscoop.beignetShop.dto;


import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitDto {
	

	private Long id;
	
	@NotEmpty(message = "error.produit.libelle.notEmpty")
	private String libelle;
	private int prix = 0;
	

}
