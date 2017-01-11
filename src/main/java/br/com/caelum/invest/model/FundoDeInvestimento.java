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
public class FundoDeInvestimento implements TipoDeInvestimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Column(scale = 3)
	private BigDecimal rentabilidade;
	@NotNull
	private BigDecimal taxaDeAdministracao = BigDecimal.ZERO.setScale(5, RoundingMode.HALF_EVEN);

	/**
	 * @deprecated
	 */
	public FundoDeInvestimento() {

	}

	public FundoDeInvestimento(BigDecimal rentabilidade, BigDecimal taxaDeAdministracao) {
		this.rentabilidade = rentabilidade;
		this.taxaDeAdministracao = taxaDeAdministracao;
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

		BigDecimal rendimento = FinancialUtils.calculaRendimentoComJuroComposto(valorAplicado, rentabilidadeMensal,
				tempoAplicado);

		BigDecimal rendimentoAposTaxaAdministracao = descontaTaxaDeAdministracao(rendimento, tempoAplicado);
		
		BigDecimal rendimentoLiquido = FinancialUtils.abateValorDoIR(rendimentoAposTaxaAdministracao, tempoAplicado);
		
		return rendimentoLiquido;

	}

	private BigDecimal descontaTaxaDeAdministracao(BigDecimal rendimento, long tempoAplicadoEmMeses) {

		BigDecimal taxaAdministracaoAoMes = taxaDeAdministracao
				.divide(new BigDecimal(12), 5, RoundingMode.HALF_EVEN);

		BigDecimal taxaCobradaNoPeriodo = taxaAdministracaoAoMes.multiply(new BigDecimal(tempoAplicadoEmMeses))
				.setScale(6, RoundingMode.HALF_EVEN);

		BigDecimal valorDevido = rendimento.multiply(taxaCobradaNoPeriodo).setScale(2, RoundingMode.HALF_EVEN);
		
		return rendimento.subtract(valorDevido);
	}

	@Override
	public String getTitulo() {
		return "Fundo de Investimento";
	}

}
