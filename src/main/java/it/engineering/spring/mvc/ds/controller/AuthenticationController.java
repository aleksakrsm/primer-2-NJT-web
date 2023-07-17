package it.engineering.spring.mvc.ds.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.engineering.spring.mvc.ds.dto.AuthenticationRequestDTO;
import it.engineering.spring.mvc.ds.service.AuthenticationService;
import it.engineering.spring.mvc.ds.validator.AuthenticationRequestValidator;

@Controller
@RequestMapping(value = "/authentication")
public class AuthenticationController {

	private AuthenticationService authenticationService;
	private AuthenticationRequestValidator authenticationRequestValidator;

	@Autowired
	public AuthenticationController(AuthenticationService authenticationService, AuthenticationRequestValidator authenticationRequestValidator) {
		this.authenticationService = authenticationService;
		this.authenticationRequestValidator = authenticationRequestValidator;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(authenticationRequestValidator);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@GetMapping(value = "/login")
	public ModelAndView login() {
		return new ModelAndView("authentication/login");
	}
	
	@PostMapping(value = "/login")
	public String login(@ModelAttribute @Validated AuthenticationRequestDTO authenticationRequest, BindingResult result, Model model) {
	    
	    if (result.hasErrors()) {
	    	return "authentication/login";
	    }
		
		try {
			System.out.println("Trying to log in user with username '" + authenticationRequest.getUsername() + "'");
			this.authenticationService.authenticate(authenticationRequest);
			System.out.println("User with username '" + authenticationRequest.getUsername() + "' is authenticated successfully");
			return "redirect:/";
		} catch (AuthenticationException e) {
			System.out.println("Authentication for user with username '" + authenticationRequest.getUsername() + "' has failed");
			model.addAttribute("invalid", "Invalid username and/or password!");
			return "authentication/login";
		}
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect:/authentication/login";
	}
	
	
	@ModelAttribute(name = "authenticationRequest")
	private AuthenticationRequestDTO createAuthenticationRequest() {
		return new AuthenticationRequestDTO();
	}
}
