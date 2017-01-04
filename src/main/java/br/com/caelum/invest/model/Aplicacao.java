
package br.com.caelum.invest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.caelum.invest.exception.AplicacaoInvalidaException;

@Entity
public class Aplicacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne 
	@NotNull
	private Conta conta;
	@ManyToOne 
	@NotNull
	private Investimento investimento;
	@NotNull
	@Column(scale=2)
	private BigDecimal valor;
	@NotNull
	private LocalDate data;
	private LocalDate dataDeResgate;
	
	/**
	 * @deprecated
	 */
	public Aplicacao() {
		
	}

	public Aplicacao(Conta conta, Investimento investimento, BigDecimal valor) {
		this.conta = conta;
		this.investimento = investimento;
		this.valor = valor;
		this.data = LocalDate.now();
	}

	public void processa() {
		if(this.valor.longValue() > this.conta.getSaldo().longValue()) {
			throw new AplicacaoInvalidaException("Saldo insuficiente para aplicação!");
		}
		
		conta.desconta(valor);
	}

	public Investimento getInvestimento() {
		return investimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getId() {
		return id;
	}

	public Conta getConta() {
		return conta;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalDate getDataPrevistaResgate() {
		return this.data.plusMonths(this.investimento.getFidelidade());
	}
	
	public LocalDate getDataDeResgate() {
		return dataDeResgate;
	}

	public void resgata() {
		this.dataDeResgate = LocalDate.now();
		
		BigDecimal valorReajustado = this.investimento.calculaRendimento(this.valor, this.data);
		conta.deposita(valorReajustado);
	}
}
