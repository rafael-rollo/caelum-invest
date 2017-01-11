package br.com.caelum.invest.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinancialUtils {

	/**
	 * Aqui calculamos o rendimento bruto, apenas considerando a regra de juro composto
	 * Valor Reajustado = Capital investido * (1 + Taxa ao mês) ^ Tempo investido em meses
	 * 
	 * @param capital Valor aplicado no ato do investimento
	 * @param taxaDeJurosAoMes Taxa percentual de juros aplicada ao mês
	 * @param tempoAplicadoEmMeses Tempo que o dinheiro permaneceu investido, em meses
	 * @return Rendimento bruto
	 */
	public static BigDecimal calculaRendimentoComJuroComposto(BigDecimal capital, 
			BigDecimal taxaDeJurosAoMes, long tempoAplicadoEmMeses) {

		double soma = 1 + taxaDeJurosAoMes.doubleValue();
		double potenciacao = Math.pow(soma, tempoAplicadoEmMeses);
		
		BigDecimal valorReajustado = capital.multiply(new BigDecimal(potenciacao))
				.setScale(2, RoundingMode.HALF_EVEN);

		
		return valorReajustado.subtract(capital);
	}

	/**
	 * Abatimento de Imposto de Renda seguindo a tabela padrão
	 * @param valorDoRendimento
	 * @param tempoAplicadoEmMeses
	 * @return Valor já deduzido imposto
	 */
	public static BigDecimal abateValorDoIR(BigDecimal valorDoRendimento, long tempoAplicadoEmMeses) {
		
		BigDecimal abatimento = null;
		
		if(tempoAplicadoEmMeses <= 6) {
			abatimento = valorDoRendimento.multiply(new BigDecimal(0.25).setScale(2, RoundingMode.HALF_EVEN));
			BigDecimal valorAposDeducao = valorDoRendimento.subtract(abatimento);
			
			return valorAposDeducao.setScale(2, RoundingMode.HALF_EVEN);
			
		} else if(tempoAplicadoEmMeses <= 12) {
			abatimento = valorDoRendimento.multiply(new BigDecimal(0.20).setScale(2, RoundingMode.HALF_EVEN));
			BigDecimal valorAposDeducao = valorDoRendimento.subtract(abatimento);
			
			return valorAposDeducao.setScale(2, RoundingMode.HALF_EVEN);
			
		} else {
			abatimento = valorDoRendimento.multiply(new BigDecimal(0.15).setScale(2, RoundingMode.HALF_EVEN));
			BigDecimal valorAposDeducao = valorDoRendimento.subtract(abatimento);
			
			return valorAposDeducao.setScale(2, RoundingMode.HALF_EVEN);
		}
	} 
}
