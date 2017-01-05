package br.com.caelum.invest.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class ContaTest {

	
	@Test
	public void testaDepositoComValorValido() {
		BigDecimal valor = new BigDecimal(1000);
		
		Conta conta = new Conta();
		conta.deposita(valor);
		
		Assert.assertEquals(new BigDecimal(1000), conta.getSaldo());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testaDepositoComValorZero() {
		BigDecimal valor = new BigDecimal(0);
		
		Conta conta = new Conta();
		conta.deposita(valor);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testaDepositoComValorNegativo() {
		BigDecimal valor = new BigDecimal(-200);
		
		Conta conta = new Conta();
		conta.deposita(valor);
	}
	
	@Test
	public void testaComValorDeDescontoMenorQueSaldo() {
		BigDecimal valorSaldo = new BigDecimal(1500);	
		BigDecimal valor = new BigDecimal(200);
		
		Conta conta = new Conta();
		conta.deposita(valorSaldo);
		conta.desconta(valor);
		
		Assert.assertEquals(new BigDecimal(1300), conta.getSaldo());
	}
	
	@Test
	public void testaComValorDeDescontoIgualQueSaldo() {
		BigDecimal valorSaldo = new BigDecimal(1500);	
		BigDecimal valor = new BigDecimal(1500);
		
		Conta conta = new Conta();
		conta.deposita(valorSaldo);
		conta.desconta(valor);
		
		Assert.assertEquals(new BigDecimal(0), conta.getSaldo());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testaComValorDeDescontoMaiorQueSaldo() {
		BigDecimal valorSaldo = new BigDecimal(300);	
		BigDecimal valor = new BigDecimal(500);
		
		Conta conta = new Conta();
		conta.deposita(valorSaldo);
		conta.desconta(valor);
	}

}
