package br.com.caelum.invest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.invest.model.Investimento;

@Entity
public class TipoDeInvestimento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String titulo;
	
	public Integer getId() {
		return id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public BigDecimal calculaRendimento(BigDecimal valorAplicado, LocalDate dataDaAplicacao, Investimento investimento) {
		return BigDecimal.TEN;
	}
}
