package com.yvanscoop.beignetShop.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AdresseLivraison implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2481991576638177014L;
	@Id @GeneratedValue
	private Long id;
	
	private String quartier;
	private int numBlock;
	private int numCase;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="utilisateur_id")
	private Utilisateur utilisateur;
	
	@JsonIgnore
	@OneToMany(mappedBy="adresseLivraison", cascade=CascadeType.ALL)
	private List<Commande> commandes = new ArrayList<>();

}
