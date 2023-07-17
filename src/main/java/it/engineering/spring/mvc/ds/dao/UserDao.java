package it.engineering.spring.mvc.ds.dao;

import it.engineering.spring.mvc.ds.entity.UserEntity;

public interface UserDao {
	UserEntity findByUsername(String username);
}
