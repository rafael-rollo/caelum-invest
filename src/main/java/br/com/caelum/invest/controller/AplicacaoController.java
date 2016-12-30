package br.com.caelum.invest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.invest.dao.AplicacaoDao;
import br.com.caelum.invest.dao.ContaDao;
import br.com.caelum.invest.dao.InvestimentoDao;
import br.com.caelum.invest.form.AplicacaoForm;
import br.com.caelum.invest.model.Aplicacao;
import br.com.caelum.invest.validator.AplicacaoFormValidator;

@Controller
@RequestMapping("/aplicacao")
public class AplicacaoController {

	@Autowired
	private ContaDao contaDao;

	@Autowired
	private InvestimentoDao investimentoDao;

	@Autowired
	private AplicacaoDao aplicacaoDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new AplicacaoFormValidator(contaDao));
	}
	
	@GetMapping("/form/conta/{contaId}")
	public String form(AplicacaoForm aplicacaoForm, Model model) {
			
		model.addAttribute("investimentos", investimentoDao.findAll());
		return "aplicacao/form";
	}
	
	@PostMapping
	public String aplica(@Valid AplicacaoForm aplicacaoForm, BindingResult result, 
			Model model) {

		if(result.hasErrors()) {
			return form(aplicacaoForm, model);
		}
		
		Aplicacao aplicacao = aplicacaoForm.build(contaDao, investimentoDao);
		aplicacao.processa();
		
		aplicacaoDao.save(aplicacao); 
		return "redirect:/conta/" + aplicacaoForm.getContaId();
	}
}
