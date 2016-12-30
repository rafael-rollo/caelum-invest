package br.com.caelum.invest.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private int numero;
	
	@ManyToOne
	private Usuario usuario;
	private BigDecimal saldo = BigDecimal.ZERO;
	
	
	@Deprecated
	public Conta() {
	}

	public Conta(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void deposita(BigDecimal valor) {
		this.saldo = this.saldo.add(valor);
	}

	public void desconta(BigDecimal valor) {
		this.saldo = this.saldo.subtract(valor);
	}
}