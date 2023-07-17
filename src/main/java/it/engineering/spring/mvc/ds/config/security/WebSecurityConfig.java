package it.engineering.spring.mvc.ds.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.engineering.spring.mvc.ds.service.UserService;

@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = {"it.engineering.spring.mvc.ds.service"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private UserService userService;
	
	@Autowired
	public WebSecurityConfig(UserService userService) {
		this.userService = userService;
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService);
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/authentication/login").permitAll()
			.antMatchers("/**").hasRole("ADMIN")
			.and()
			.formLogin().loginPage("/authentication/login").loginProcessingUrl("authentication/login").permitAll();
		
		http.csrf().disable();
	}
	
	@Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.GET, "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.png", "/**/*.gif");
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
