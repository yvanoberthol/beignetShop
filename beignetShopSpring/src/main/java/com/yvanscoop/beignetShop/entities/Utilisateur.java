package com.yvanscoop.beignetShop.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Utilisateur implements Serializable, UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8866050358482398713L;

	@Id @GeneratedValue
	private Long id;
	
	private String nomComplet;
	
	private int phoneNumber;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	private String role;
	private boolean active = true;
	
	@OneToMany(mappedBy="utilisateur", cascade=CascadeType.ALL)
	private List<AdresseLivraison> adresseLivraisons = new ArrayList<>();

	@OneToMany(mappedBy="utilisateur", cascade=CascadeType.ALL)
	private List<Panier> paniers = new ArrayList<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new Authority(this.role));
        return authorities;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.active;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.active;
	}
}
