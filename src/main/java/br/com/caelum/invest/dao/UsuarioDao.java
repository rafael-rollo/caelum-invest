package br.com.caelum.invest.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.caelum.invest.model.Usuario;

@Repository
public class UsuarioDao implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			return manager
					.createQuery("select u from Usuario u where u.email = :username", Usuario.class)
					.setParameter("username", username)
					.getSingleResult();

		} catch (NoResultException e) {
			throw new UsernameNotFoundException("Usuário com email " + username + " não foi encontrado");
		}
	}

}
