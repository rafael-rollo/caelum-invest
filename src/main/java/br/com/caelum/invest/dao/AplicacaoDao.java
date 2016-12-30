package br.com.caelum.invest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.caelum.invest.model.Aplicacao;

@Repository
public interface AplicacaoDao extends CrudRepository<Aplicacao, Integer>{

}
