package br.com.caelum.invest.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.invest.exception.AplicacaoInvalidaException;

public class AplicacaoTest {
	
	@Test
	public void testaProcessamentoComValoresIguais() {
		BigDecimal valorAplicado = new BigDecimal(1000);
		BigDecimal saldoDaConta = new BigDecimal(1000);
		
		Conta conta = new Conta();
		conta.deposita(saldoDaConta);
		
		Aplicacao aplicacao = new Aplicacao(conta, null, valorAplicado);
		aplicacao.processa();
		
		Assert.assertEquals(conta.getSaldo(), new BigDecimal(0));
		
	}
	
	@Test(expected=AplicacaoInvalidaException.class)
	public void testaProcessamentoComValorDeAplicacaoMaiorQueSaldo() {
		BigDecimal valorAplicado = new BigDecimal(2000);
		BigDecimal saldoDaConta = new BigDecimal(400);
		
		Conta conta = new Conta();
		conta.deposita(saldoDaConta);
		
		Aplicacao aplicacao = new Aplicacao(conta, null, valorAplicado);
		aplicacao.processa();
	}

	
	@Test
	public void testaProcessamentoComValorDeAplicacaoMenorQueSaldo() {
	BigDecimal valorAplicado = new BigDecimal(300);
	BigDecimal saldoDaConta = new BigDecimal(800);
	
	Conta conta = new Conta();
	conta.deposita(saldoDaConta);
	
	Aplicacao aplicacao = new Aplicacao(conta, null, valorAplicado);
	aplicacao.processa();
	
	Assert.assertEquals(conta.getSaldo(), new BigDecimal(500));
	}
}
