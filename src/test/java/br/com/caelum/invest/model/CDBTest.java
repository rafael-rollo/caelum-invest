package br.com.caelum.invest.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Test;



public class CDBTest {

	@Test
	public void deveCalcularRetornoDeAplicacaoEmUmMes() throws Exception {
		
		BigDecimal valorAplicado = new BigDecimal(300).setScale(2, RoundingMode.HALF_EVEN);
		LocalDate umMesAtras = LocalDate.now().minus(1, ChronoUnit.MONTHS);
		
		BigDecimal rentabilidadeAnual = new BigDecimal(0.36).setScale(3, RoundingMode.HALF_EVEN);
		CDB cdb = new CDB(rentabilidadeAnual);
		
		BigDecimal rendimentoLiquido = cdb.calculaRendimento(valorAplicado, umMesAtras);
		Assert.assertEquals(new BigDecimal(6.75).setScale(2, RoundingMode.HALF_EVEN), rendimentoLiquido);
		
	}
	
	@Test
	public void deveCalcularRetornoDeAplicacaoEmUmSemestre() throws Exception {
		
		BigDecimal valorAplicado = new BigDecimal(300).setScale(2, RoundingMode.HALF_EVEN);
		LocalDate umSemestreAtras = LocalDate.now().minus(6, ChronoUnit.MONTHS);
		
		BigDecimal rentabilidadeAnual = new BigDecimal(0.36).setScale(3, RoundingMode.HALF_EVEN);
		CDB cdb = new CDB(rentabilidadeAnual);
		
		BigDecimal rendimentoLiquido = cdb.calculaRendimento(valorAplicado, umSemestreAtras);
		Assert.assertEquals(new BigDecimal(43.66).setScale(2, RoundingMode.HALF_EVEN), rendimentoLiquido);
		
	}
	
	@Test
	public void deveCalcularRetornoDeAplicacaoEmUmAno() throws Exception {
		
		BigDecimal valorAplicado = new BigDecimal(300).setScale(2, RoundingMode.HALF_EVEN);
		LocalDate umAnoAtras = LocalDate.now().minus(12, ChronoUnit.MONTHS);
		System.out.println(umAnoAtras);
		
		BigDecimal rentabilidadeAnual = new BigDecimal(0.36).setScale(3, RoundingMode.HALF_EVEN);
		CDB cdb = new CDB(rentabilidadeAnual);
		
		BigDecimal rendimentoLiquido = cdb.calculaRendimento(valorAplicado, umAnoAtras);
		Assert.assertEquals(new BigDecimal(102.18).setScale(2, RoundingMode.HALF_EVEN), rendimentoLiquido);
		
	}
	
}
