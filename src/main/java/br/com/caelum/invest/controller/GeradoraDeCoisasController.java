package br.com.caelum.invest.controller;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.caelum.invest.dao.UsuarioDao;
import br.com.caelum.invest.model.Conta;
import br.com.caelum.invest.model.Role;
import br.com.caelum.invest.model.Usuario;

@Controller
@Transactional
public class GeradoraDeCoisasController {

	@Autowired
	private UsuarioDao usuarioDao;

	@PersistenceContext
	private EntityManager em;

	@ResponseBody
	@GetMapping("/gera/usuario")
	public String geraUsuario() {
		
		Role role = new Role();
		role.setNome("ROLE_ADMIN");
		em.persist(role);
		
		Usuario usuario = new Usuario();
		usuario.setEmail("admin@corretora.com.br");
		usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
		usuario.addRole(role);
		em.persist(usuario);
		
		return "um usuario admin foi inserido";
	}
	
	@ResponseBody
	@GetMapping("/gera/conta")
	public String geraConta(String email) {
		
		Usuario usuario = usuarioDao.findByEmail(email);
		Conta conta = new Conta(usuario);
		em.persist(conta);
		
		String year = String.valueOf(LocalDate.now().getYear());
		String n = conta.getId() * .01 + "";
				
		conta.setNumero(Integer.parseInt(year + n.replace(".", "")));
		return "uma nova conta foi gerada para o usuario";
	}

}
