package br.com.caelum.invest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.caelum.invest.dao.ContaDao;
import br.com.caelum.invest.model.Usuario;

@Controller("/conta")
public class ContaController {

	@Autowired
	private ContaDao contaDao;

	@GetMapping
	public String lista(@AuthenticationPrincipal Usuario usuario, Model model) {

		model.addAttribute("contas", contaDao.findByUsuario(usuario));
		return "conta/dashboard";
	}
}
