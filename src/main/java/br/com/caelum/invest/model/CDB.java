package br.com.caelum.invest.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import br.com.caelum.invest.utils.FinancialUtils;

@Entity
public class CDB implements TipoDeInvestimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Column(scale = 3)
	private BigDecimal rentabilidade = BigDecimal.ZERO.setScale(3, RoundingMode.HALF_UP);

	/**
	 * @deprecated
	 */
	public CDB() {

	}

	public CDB(BigDecimal rentabilidade) {
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
		
		long tempoAplicado = dataDaAplicacao.until(LocalDate.now(), ChronoUnit.MONTHS);
		
		BigDecimal rentabilidadeMensal = this.rentabilidade.divide(new BigDecimal(12));
		
		BigDecimal rendimento = FinancialUtils
				.calculaRendimentoComJuroComposto(valorAplicado, rentabilidadeMensal, tempoAplicado);
		
		BigDecimal rendimentoLiquido = FinancialUtils.abateValorDoIR(rendimento, tempoAplicado);
		
		return rendimentoLiquido;
	}

	@Override
	public String getTitulo() {
		return "CDB";
	}

}
