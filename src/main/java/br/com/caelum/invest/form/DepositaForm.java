package br.com.caelum.invest.form;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DepositaForm {
	
	@Min(1)
	private Integer contaId;
	@NotNull @Min(1)
	private BigDecimal valor = BigDecimal.ZERO;
	
	public Integer getContaId() {
		return contaId;
	}
	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
}
