package com.yvanscoop.beignetShop.entities;

import java.io.Serializable;
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
public class Plat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3472106546652527171L;
	@Id @GeneratedValue
	private Long id;
	private String code;
	
	@OneToMany(mappedBy="plat", cascade=CascadeType.ALL)
	private List<LignePlat> lignePlats;
	
	private int montant = 0;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="panier_id")
	private Panier panier;
}
