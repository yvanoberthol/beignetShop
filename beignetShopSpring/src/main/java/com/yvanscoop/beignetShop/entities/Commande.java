package com.yvanscoop.beignetShop.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Commande implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8234813676493916680L;

	@Id @GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private String code;
	
	@CreatedDate
	private Date date = new Date();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	private Panier panier;
	
	private int montant = 0;
	
	@ManyToOne
	@JoinColumn(name="adresse_livraison_id")
	private AdresseLivraison adresseLivraison;
	
	@PrePersist @PreUpdate
	private void setMontant() {
		panier.getPlats().forEach(plat -> {
			plat.getLignePlats().forEach(lignePlat -> {
				this.montant += lignePlat.getMontant();
			});
		});
	}
	
}
