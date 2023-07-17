package it.engineering.spring.mvc.ds.service;
import org.springframework.security.core.AuthenticationException;
import it.engineering.spring.mvc.ds.dto.AuthenticationRequestDTO;

public interface AuthenticationService {
	void authenticate(AuthenticationRequestDTO authenticationRequestDTO) throws AuthenticationException;
}