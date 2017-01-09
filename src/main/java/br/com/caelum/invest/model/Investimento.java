package br.com.caelum.invest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

@Entity
public class Investimento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int fidelidade;
	@NotNull @Min(1)
	private BigDecimal aporteMinimo;
	
	@NotNull
	@Any(metaColumn = @Column(name = "investimento_tipo"))
	@AnyMetaDef(idType = "int", metaType = "string", metaValues = {
			@MetaValue(value = "CDB", targetEntity = CDB.class),
			@MetaValue(value = "Fundo de Investimento", targetEntity = FundoDeInvestimento.class) })
	@JoinColumn(name = "tipo_id")
	private TipoDeInvestimento tipoDeInvestimento;
	
	/**
	 * @deprecated
	 */
	public Investimento() {
		
	}
	
	public Investimento(int fidelidade, BigDecimal aporteMinimo, TipoDeInvestimento tipoDeInvestimento) {
		this.fidelidade = fidelidade;
		this.aporteMinimo = aporteMinimo;
		this.tipoDeInvestimento = tipoDeInvestimento;
	}
	
	public Integer getId() {
		return id;
	}

	public int getFidelidade() {
		return this.fidelidade;
	}
	
	public TipoDeInvestimento getTipoDeInvestimento() {
		return this.tipoDeInvestimento;
	}
	
	public BigDecimal calculaRendimento(BigDecimal valorAplicado, LocalDate dataDaAplicacao) {
		return this.tipoDeInvestimento.calculaRendimento(valorAplicado, dataDaAplicacao);
	}

	public BigDecimal getAporteMinimo() {
		return this.aporteMinimo;
	}
	
	public boolean ehPossivelInvestir(BigDecimal valor) {
		return valor.longValue() >= this.aporteMinimo.longValue();
	}
	
}
