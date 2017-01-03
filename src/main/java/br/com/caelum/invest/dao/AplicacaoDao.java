package br.com.caelum.invest.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.caelum.invest.model.Aplicacao;
import br.com.caelum.invest.model.Conta;

@Repository
public interface AplicacaoDao extends CrudRepository<Aplicacao, Integer>{
	
	List<Aplicacao> findByConta(Conta conta);
}
