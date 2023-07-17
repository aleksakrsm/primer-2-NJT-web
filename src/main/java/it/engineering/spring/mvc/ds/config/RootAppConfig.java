package it.engineering.spring.mvc.ds.config;

import javax.validation.Valid;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import it.engineering.spring.mvc.ds.config.security.WebSecurityConfig;

@Configuration
@ComponentScan(basePackages = {
		"it.engineering.spring.mvc.ds.service",
		"it.engineering.spring.mvc.ds.dao",
		"it.engineering.spring.mvc.ds.converter",
		"it.engineering.spring.mvc.ds.validator"
})
@Import(value = {DatabaseConfig.class,WebSecurityConfig.class})//WebSecurityConfig.class
public class RootAppConfig {
	@Bean
	public Validator localValidatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setProviderClass(org.hibernate.validator.HibernateValidator.class);
		return bean;
	}
}
