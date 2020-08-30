package com.yvanscoop.beignetShop.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LignePlat implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4700444799957358752L;

	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="produit_id")
	private Produit produit;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="plat_id")
	private Plat plat;
	
	private int montant;

}
