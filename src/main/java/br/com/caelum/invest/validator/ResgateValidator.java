package br.com.caelum.invest.validator;

import java.time.LocalDate;

import br.com.caelum.invest.model.Aplicacao;

public class ResgateValidator {
	
	private final LocalDate DATA_ATUAL = LocalDate.now(); 

	public boolean validate(Aplicacao aplicacao) {
		if(! aplicacao.ehPossivelResgatar(DATA_ATUAL))
			return false;
		
		return true;
	}
	
	
	
}
