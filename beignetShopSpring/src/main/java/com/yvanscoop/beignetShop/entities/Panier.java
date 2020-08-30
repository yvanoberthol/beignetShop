package com.yvanscoop.beignetShop.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Panier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7805464047604142931L;

	@Id @GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private String code;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="utilisateur_id")
	private Utilisateur utilisateur;
	
	private Boolean confirmated = false;
	
	@JsonIgnore
	@OneToOne(mappedBy="panier")
	private Commande commande;
	
	@OneToMany(mappedBy="panier", cascade=CascadeType.ALL)
	private List<Plat> plats;
	
}
