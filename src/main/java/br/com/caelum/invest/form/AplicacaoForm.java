package br.com.caelum.invest.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import br.com.caelum.invest.dao.ContaDao;
import br.com.caelum.invest.dao.InvestimentoDao;
import br.com.caelum.invest.model.Aplicacao;
import br.com.caelum.invest.model.Conta;
import br.com.caelum.invest.model.Investimento;

public class AplicacaoForm {

	@NotNull
	private Integer contaId;
	@NotNull
	private Integer investimentoId;
	@NotNull
	private BigDecimal valor = BigDecimal.ZERO;

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Integer getInvestimentoId() {
		return investimentoId;
	}

	public void setInvestimentoId(Integer investimentoId) {
		this.investimentoId = investimentoId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Aplicacao build(ContaDao contaDao, 
			InvestimentoDao investimentoDao) {

		Conta conta = contaDao.find(this.contaId);
		Investimento investimento = investimentoDao.findOne(this.investimentoId);
		
		return new Aplicacao(conta, investimento, valor);
	}
}
