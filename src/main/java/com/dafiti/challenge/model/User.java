package com.dafiti.challenge.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String client;
    private String pass;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfil = new ArrayList<>();

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfil;
    }
    @Override
    public String getPassword() {
        return this.pass;
    }
    @Override
    public String getUsername() {
        return this.client;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    
}
