package br.com.caelum.invest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
public class Role implements GrantedAuthority {

	@Id
	private String nome;
	
	@Override
	public String getAuthority() {
		return this.nome;
	}

}
