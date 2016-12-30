package br.com.caelum.invest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.invest.model.Conta;
import br.com.caelum.invest.model.Usuario;

@Repository
public class ContaDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Conta> findByUsuario(Usuario usuario) {
		
		String jpql = "select c from Conta c where c.usuario = :usuario";
		return manager.createQuery(jpql, Conta.class)
				.setParameter("usuario", usuario)
				.getResultList();
	}

	public Conta find(Integer id) {
		String jpql = "select c from Conta c where c.id = :id";
		return manager.createQuery(jpql, Conta.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	
}
