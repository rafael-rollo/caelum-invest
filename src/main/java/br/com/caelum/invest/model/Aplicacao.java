package br.com.caelum.invest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.caelum.invest.model.Conta;
import br.com.caelum.invest.model.Investimento;

@Entity
public class Aplicacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne 
	@NotNull
	private Conta conta;
	@ManyToOne 
	@NotNull
	private Investimento investimento;
	@NotNull
	@Column(scale=2)
	private BigDecimal valor;
	@NotNull
	private LocalDate data;
	private LocalDate dataDeResgate;
	
	/**
	 * @deprecated
	 */
	public Aplicacao() {
		
	}

	public Aplicacao(Conta conta, Investimento investimento, BigDecimal valor) {
		this.conta = conta;
		this.investimento = investimento;
		this.valor = valor;
		this.data = LocalDate.now();
	}

	public void processa() {
		if(this.valor.longValue() > this.conta.getSaldo().longValue()) {
			throw new IllegalStateException();
		}
		
		conta.desconta(valor);
	}
	
}
