package br.com.caelum.invest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class FundoDeInvestimento implements TipoDeInvestimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Column(scale=4)
	private BigDecimal rentabilidade;
	
	/**
	 * @deprecated
	 */
	public FundoDeInvestimento() {

	}

	public FundoDeInvestimento(BigDecimal rentabilidade) {
		this.rentabilidade = rentabilidade;
	}
	
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public BigDecimal getRentabilidade() {
		return this.rentabilidade;
	}

	@Override
	public BigDecimal calculaRendimento(BigDecimal valorAplicado, LocalDate dataDaAplicacao) {
		return new BigDecimal(50).setScale(2);
	}

	@Override
	public String getTitulo() {
		return "Fundo de Investimento";
	}

}
