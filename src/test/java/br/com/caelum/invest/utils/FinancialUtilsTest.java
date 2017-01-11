package br.com.caelum.invest.utils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class FinancialUtilsTest {

	@Test
	public void deveCalcularRendimentoExatoParaUmMes() throws Exception {
		
		BigDecimal valorAplicado = new BigDecimal(300).setScale(2);
		BigDecimal rentabilidade = new BigDecimal(0.03).setScale(3, RoundingMode.HALF_UP);
		
		
		BigDecimal valorReajustado = FinancialUtils
				.calculaRendimentoComJuroComposto(valorAplicado, rentabilidade, 1);
		
		assertEquals(new BigDecimal(9).setScale(2, RoundingMode.HALF_EVEN), valorReajustado);
	}
	
	@Test
	public void deveCalcularRendimentoExatoParaUmSemestre() throws Exception {
		
		BigDecimal valorAplicado = new BigDecimal(300).setScale(2);
		BigDecimal rentabilidade = new BigDecimal(0.03).setScale(3, RoundingMode.HALF_EVEN);
		
		BigDecimal valorReajustado = FinancialUtils
				.calculaRendimentoComJuroComposto(valorAplicado, rentabilidade, 6);
		
		assertEquals(new BigDecimal(58.22).setScale(2, RoundingMode.HALF_EVEN), valorReajustado);
	}
	
	@Test
	public void deveCalcularRendimentoExatoParaUmAno() throws Exception {
		
		BigDecimal valorAplicado = new BigDecimal(300).setScale(2);
		BigDecimal rentabilidade = new BigDecimal(0.03).setScale(3, RoundingMode.HALF_EVEN);
		
		BigDecimal valorReajustado = FinancialUtils
				.calculaRendimentoComJuroComposto(valorAplicado, rentabilidade, 12);
		
		assertEquals(new BigDecimal(127.73).setScale(2, RoundingMode.HALF_EVEN), valorReajustado);
	}
	
	@Test
	public void deveAbaterAliquotaDe25PorCento() throws Exception {
		
		BigDecimal rendimentoBruto = new BigDecimal(100).setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal valorAposDeducao = FinancialUtils.abateValorDoIR(rendimentoBruto, 6);
		
		assertEquals(new BigDecimal(75).setScale(2, RoundingMode.HALF_EVEN),
				valorAposDeducao);
	}
	
	@Test
	public void deveAbaterAliquotaDe20PorCento() throws Exception {
		BigDecimal rendimentoBruto = new BigDecimal(100).setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal valorAposDeducao = FinancialUtils.abateValorDoIR(rendimentoBruto, 12);
		
		assertEquals(new BigDecimal(80).setScale(2, RoundingMode.HALF_EVEN),
				valorAposDeducao);
	}
	
	@Test
	public void deveAbaterAliquotaDe15PorCento() throws Exception {
		BigDecimal rendimentoBruto = new BigDecimal(100).setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal valorAposDeducao = FinancialUtils.abateValorDoIR(rendimentoBruto, 18);
		
		assertEquals(new BigDecimal(85).setScale(2, RoundingMode.HALF_EVEN),
				valorAposDeducao);
	}
}
