package it.engineering.spring.mvc.ds.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.engineering.spring.mvc.ds.dao.UserDao;
import it.engineering.spring.mvc.ds.entity.UserEntity;
@Repository
public class JpaUserDaoImpl implements UserDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public UserEntity findByUsername(String username) {
		return entityManager
				.createQuery("SELECT u from UserEntity u WHERE u.username=:username", UserEntity.class)
				.setParameter("username", username)
				.getSingleResult();
	}

}
