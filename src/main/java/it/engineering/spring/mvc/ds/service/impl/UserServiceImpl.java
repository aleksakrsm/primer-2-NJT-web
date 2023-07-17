package it.engineering.spring.mvc.ds.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.engineering.spring.mvc.ds.dao.impl.JpaUserDaoImpl;
import it.engineering.spring.mvc.ds.entity.UserEntity;
import it.engineering.spring.mvc.ds.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	private JpaUserDaoImpl userRepository;
	
	@Autowired
	public UserServiceImpl(JpaUserDaoImpl userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = this.userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User with username '" + username + "' does not exist!");
		}
		
		return user;
	} 
}