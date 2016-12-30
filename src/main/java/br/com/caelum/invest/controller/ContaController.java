package br.com.caelum.invest.controller;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.invest.model.Conta;
import br.com.caelum.invest.dao.ContaDao;
import br.com.caelum.invest.model.Usuario;

@Controller
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaDao contaDao;

	@GetMapping
	public String lista(@AuthenticationPrincipal Usuario usuario, Model model) {

		model.addAttribute("contas", contaDao.findByUsuario(usuario));
		return "conta/dashboard";
	}
	
	@GetMapping("/{id}")
	public String detalhes(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("conta", contaDao.find(id));
		return "conta/detalhes";
	}
	
	@GetMapping("/{id}/deposito/form")
	public String depositoForm(@PathVariable("id") Integer id, Model model) {

		model.addAttribute("conta", contaDao.find(id));
		return "conta/deposito-form";
	}
	
	@PostMapping("/deposito")
	@Transactional
	public String deposita(int contaId, BigDecimal valor) {
		
		Conta conta = contaDao.find(contaId);
		conta.deposita(valor);
		
		return "redirect:/conta/" + contaId;
	}
}
