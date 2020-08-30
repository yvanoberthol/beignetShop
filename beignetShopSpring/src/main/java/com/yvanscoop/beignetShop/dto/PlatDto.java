package com.yvanscoop.beignetShop.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlatDto {
	private Long id;
	private String code;
	private Long panier_id;
	private int montant;
	private List<LignePlatDto> lignePlats;
}
