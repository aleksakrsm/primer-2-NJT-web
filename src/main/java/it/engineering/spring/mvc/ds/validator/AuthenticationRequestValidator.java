package it.engineering.spring.mvc.ds.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.engineering.spring.mvc.ds.dto.AuthenticationRequestDTO;
@Component
public class AuthenticationRequestValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return AuthenticationRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AuthenticationRequestDTO authenticationRequest = (AuthenticationRequestDTO) target;
		
		System.out.println("Validating authentication request with username: " + authenticationRequest.getUsername());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.login.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.login.password");
		
		if (errors.hasErrors()) {
			return;
		}
		
		if (authenticationRequest.getUsername().length() < 4 || authenticationRequest.getUsername().length() > 32) {
			errors.rejectValue("username", "Valid.login.username");
		}
		
		if (authenticationRequest.getPassword().length() < 6 || authenticationRequest.getPassword().length() > 32) {
			errors.rejectValue("password", "Valid.login.password");
		}
	}	
}