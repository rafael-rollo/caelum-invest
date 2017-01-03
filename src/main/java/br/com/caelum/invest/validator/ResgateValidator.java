package br.com.caelum.invest.validator;

import java.time.LocalDate;

import br.com.caelum.invest.model.Aplicacao;

public class ResgateValidator {

	public boolean validate(Aplicacao aplicacao) {
		if(LocalDate.now().isBefore(aplicacao.getData().plusMonths(aplicacao.getInvestimento().getFidelidade())))
			return false;
		
		return true;
	}
}
