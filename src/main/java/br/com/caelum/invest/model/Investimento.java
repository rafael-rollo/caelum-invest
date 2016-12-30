package br.com.caelum.invest.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.caelum.invest.model.TipoDeInvestimento;

@Entity
public class Investimento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int fidelidade;
	@NotNull
	@Column(scale=4)
	private BigDecimal rentabilidade;
	
	@ManyToOne
	@NotNull
	private TipoDeInvestimento tipoDeInvestimento;
	
	@Deprecated
	public Investimento() {
		
	}
	
	public Investimento(int fidelidade, BigDecimal rentabilidade, TipoDeInvestimento tipoDeInvestimento) {
		this.fidelidade = fidelidade;
		this.rentabilidade = rentabilidade;
		this.tipoDeInvestimento = tipoDeInvestimento;
	}
	
	public Integer getId() {
		return id;
	}

	public int getFidelidade() {
		return this.fidelidade;
	}
	
	public BigDecimal getRentabilidade() {
		return this.rentabilidade;
	}
	
	public TipoDeInvestimento getTipoDeInvestimento() {
		return this.tipoDeInvestimento;
	}
	
	public BigDecimal calculaRendimento() {
		return this.tipoDeInvestimento.calculaRendimento(this);
	}
	
}
