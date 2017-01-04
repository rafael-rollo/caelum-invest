package br.com.caelum.invest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.invest.dao.AplicacaoDao;
import br.com.caelum.invest.model.Aplicacao;

@Service
public class RegistraAplicacaoService {

	@Autowired
	private AplicacaoDao aplicacaoDao;

	public void processa(Aplicacao aplicacao) {
		aplicacao.processa();
		aplicacaoDao.save(aplicacao);
	}
}
