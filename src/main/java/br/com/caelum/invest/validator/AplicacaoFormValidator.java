package br.com.caelum.invest.validator;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.caelum.invest.dao.ContaDao;
import br.com.caelum.invest.form.AplicacaoForm;
import br.com.caelum.invest.model.Conta;

public class AplicacaoFormValidator implements Validator {

	private ContaDao contaDao;
	private final BigDecimal APORTE_MINIMO = BigDecimal.TEN;

	public AplicacaoFormValidator(ContaDao contaDao) {
		this.contaDao = contaDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return AplicacaoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		AplicacaoForm aplicacaoForm = (AplicacaoForm) target;

		if (aplicacaoForm.getValor().longValue() < APORTE_MINIMO.longValue()) {
			errors.rejectValue("valor", "valor.menor.aporteMinimo",
					"O aporte mínimo para esse tipo de investimento é: " + APORTE_MINIMO);
		}

		Conta conta = contaDao.find(aplicacaoForm.getContaId());
		BigDecimal subtract = conta.getSaldo().subtract(aplicacaoForm.getValor());
		
		if (subtract.longValue() < 0) {
			errors.rejectValue("valor", "valor.inconsistente", "Saldo insuficiente para investimento!");
		}

	}
}
