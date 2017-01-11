package br.com.caelum.invest.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Test;

public class FundoDeInvestimentoTest {

	@Test
	public void deveCalcularRetornoDeAplicacaoEmUmMes() throws Exception {
		
		BigDecimal valorAplicado = new BigDecimal(300).setScale(2, RoundingMode.HALF_EVEN);
		LocalDate umMesAtras = LocalDate.now().minus(1, ChronoUnit.MONTHS);
		
		BigDecimal rentabilidadeAnual = new BigDecimal(0.36).setScale(3, RoundingMode.HALF_EVEN);
		BigDecimal taxaAdministracaoAnual = new BigDecimal(0.01).setScale(6, RoundingMode.HALF_EVEN);
		
		FundoDeInvestimento fundo = new FundoDeInvestimento(rentabilidadeAnual, taxaAdministracaoAnual);
		
		BigDecimal rendimentoLiquido = fundo.calculaRendimento(valorAplicado, umMesAtras);
		Assert.assertEquals(new BigDecimal(6.74).setScale(2, RoundingMode.HALF_EVEN), rendimentoLiquido);
		
	}
	
	@Test
	public void deveCalcularRetornoDeAplicacaoEmSemestre() throws Exception {
		
		BigDecimal valorAplicado = new BigDecimal(300).setScale(2, RoundingMode.HALF_EVEN);
		LocalDate umSemestreAtras = LocalDate.now().minus(6, ChronoUnit.MONTHS);
		
		BigDecimal rentabilidadeAnual = new BigDecimal(0.36).setScale(3, RoundingMode.HALF_EVEN);
		BigDecimal taxaAdministracaoAnual = new BigDecimal(0.01).setScale(6, RoundingMode.HALF_EVEN);
		
		FundoDeInvestimento fundo = new FundoDeInvestimento(rentabilidadeAnual, taxaAdministracaoAnual);
		
		BigDecimal rendimentoLiquido = fundo.calculaRendimento(valorAplicado, umSemestreAtras);
		Assert.assertEquals(new BigDecimal(43.45).setScale(2, RoundingMode.HALF_EVEN), rendimentoLiquido);
		
	}
	
	@Test
	public void deveCalcularRetornoDeAplicacaoEmAno() throws Exception {
		
		BigDecimal valorAplicado = new BigDecimal(300).setScale(2, RoundingMode.HALF_EVEN);
		LocalDate umAnoAtras = LocalDate.now().minus(12, ChronoUnit.MONTHS);
		
		BigDecimal rentabilidadeAnual = new BigDecimal(0.36).setScale(3, RoundingMode.HALF_EVEN);
		BigDecimal taxaAdministracaoAnual = new BigDecimal(0.01).setScale(6, RoundingMode.HALF_EVEN);
		
		FundoDeInvestimento fundo = new FundoDeInvestimento(rentabilidadeAnual, taxaAdministracaoAnual);
		
		BigDecimal rendimentoLiquido = fundo.calculaRendimento(valorAplicado, umAnoAtras);
		Assert.assertEquals(new BigDecimal(101.17).setScale(2, RoundingMode.HALF_EVEN), rendimentoLiquido);
		
	}
	
}
