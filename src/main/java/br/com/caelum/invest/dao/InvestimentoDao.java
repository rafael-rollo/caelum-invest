package br.com.caelum.invest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.caelum.invest.model.Investimento;

@Repository
public interface InvestimentoDao extends CrudRepository<Investimento, Integer> {

}
