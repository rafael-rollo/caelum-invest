package br.com.caelum.invest.validator;

import java.math.BigDecimal;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.caelum.invest.dao.ContaDao;
import br.com.caelum.invest.dao.InvestimentoDao;
import br.com.caelum.invest.form.AplicacaoForm;
import br.com.caelum.invest.model.Conta;
import br.com.caelum.invest.model.Investimento;

public class AplicacaoFormValidator implements Validator {

	private ContaDao contaDao;
	private InvestimentoDao investimentoDao;

	public AplicacaoFormValidator(ContaDao contaDao, InvestimentoDao investimentoDao) {
		this.contaDao = contaDao;
		this.investimentoDao = investimentoDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return AplicacaoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		AplicacaoForm aplicacaoForm = (AplicacaoForm) target;
		
		if(aplicacaoForm.getInvestimentoId() == null) {
			errors.rejectValue("investimentoId", "investimento.obrigatorio", "Selecione o investimento!");
			return;
		}
		
		Investimento investimento = investimentoDao
				.findOne(aplicacaoForm.getInvestimentoId());

		if (aplicacaoForm.getValor().longValue() < investimento.getAporteMinimo().longValue()) {
			errors.rejectValue("valor", "valor.menor.aporteMinimo",
					"O aporte mínimo para esse tipo de investimento é: " + investimento.getAporteMinimo());
		}

		Conta conta = contaDao.find(aplicacaoForm.getContaId());
		BigDecimal diferenca = conta.getSaldo().subtract(aplicacaoForm.getValor());
		
		if (diferenca.longValue() < 0) {
			errors.rejectValue("valor", "valor.inconsistente", "Saldo insuficiente para investimento!");
		}

	}

}
