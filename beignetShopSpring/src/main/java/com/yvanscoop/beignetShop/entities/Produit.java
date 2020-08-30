package com.yvanscoop.beignetShop.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Produit implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4934797131060219482L;
	@Id @GeneratedValue
	private Long id;
	@Column(unique = true)
	private String libelle;
	private int prix = 0;
	
	@JsonIgnore
	@OneToMany(mappedBy="produit", cascade=CascadeType.ALL)
	private List<LignePlat> lignePlats;
}
