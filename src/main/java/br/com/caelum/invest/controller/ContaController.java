package br.com.caelum.invest.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.invest.dao.AplicacaoDao;
import br.com.caelum.invest.dao.ContaDao;
import br.com.caelum.invest.form.DepositaForm;
import br.com.caelum.invest.model.Conta;
import br.com.caelum.invest.model.Usuario;

@Controller
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaDao contaDao;
	
	@Autowired
	private AplicacaoDao aplicacaoDao;

	@GetMapping
	public String lista(@AuthenticationPrincipal Usuario usuario, Model model) {

		model.addAttribute("contas", contaDao.findByUsuario(usuario));
		return "conta/dashboard";
	}
	
	@GetMapping("/{id}")
	public String detalhes(@PathVariable("id") Integer id, Model model) {
		
		Conta conta = contaDao.find(id);
		
		model.addAttribute("aplicacoes", aplicacaoDao.findByConta(conta));
		model.addAttribute("conta", conta);
		return "conta/detalhes";
	}
	
	@GetMapping("/{contaId}/deposito/form")
	public String depositoForm(DepositaForm depositaForm, Model model) {

		model.addAttribute("conta", contaDao.find(depositaForm.getContaId()));
		return "conta/deposito-form";
	}
	
	@PostMapping("/deposito")
	@Transactional
	public String deposita(@Valid DepositaForm depositaForm, BindingResult binding, Model model) {
		
		if(binding.hasErrors()) {
			return depositoForm(depositaForm, model);
		}
		
		Conta conta = contaDao.find(depositaForm.getContaId());
		conta.deposita(depositaForm.getValor());
		
		return "redirect:/conta/" + depositaForm.getContaId();
	}
}
