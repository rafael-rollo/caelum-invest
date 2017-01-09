package br.com.caelum.invest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TipoDeInvestimento {
	
	Integer getId();
	
	String getTitulo();
	
	BigDecimal getRentabilidade();

	BigDecimal calculaRendimento(BigDecimal valorAplicado, LocalDate dataDaAplicacao);
}
