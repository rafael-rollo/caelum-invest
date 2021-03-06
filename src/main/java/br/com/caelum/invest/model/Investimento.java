package br.com.caelum.invest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.caelum.invest.model.TipoDeInvestimento;

@Entity
public class Investimento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int fidelidade;
	@NotNull @Min(1)
	private BigDecimal aporteMinimo;
	@NotNull
	@Column(scale=4)
	private BigDecimal rentabilidade;
	
	@ManyToOne
	@NotNull
	private TipoDeInvestimento tipoDeInvestimento;
	
	@Deprecated
	public Investimento() {
		
	}
	
	public Investimento(int fidelidade, BigDecimal aporteMinimo, BigDecimal rentabilidade, TipoDeInvestimento tipoDeInvestimento) {
		this.fidelidade = fidelidade;
		this.aporteMinimo = aporteMinimo;
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
	
	public BigDecimal calculaRendimento(BigDecimal valorAplicado, LocalDate dataDaAplicacao) {
		return this.tipoDeInvestimento.calculaRendimento(valorAplicado, dataDaAplicacao, this);
	}

	public BigDecimal getAporteMinimo() {
		return this.aporteMinimo;
	}
	
	public boolean ehPossivelInvestir(BigDecimal valor) {
		return valor.longValue() >= this.aporteMinimo.longValue();
	}
	
}
