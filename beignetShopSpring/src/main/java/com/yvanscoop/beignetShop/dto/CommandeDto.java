package com.yvanscoop.beignetShop.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandeDto {

	private Long id;

	private Long panierId;
	
	private Long adresseLivraisonId;
}
