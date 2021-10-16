//package com.revature.security;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.revature.services.TrainerService;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	private final TrainerService ts;
	

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder)  {
		this.passwordEncoder = passwordEncoder;
		this.ts = new TrainerService();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{ //Let's call CSRF protection a stretch goal
		http.csrf().disable() //TODO: pull data from cookie on FE, send with reqs cross-site request forgery: see 1:57:00 amigoscode Spring Security full course
//			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //allows us to pull values from the cookie
//			.and()
			.authorizeRequests()
			.antMatchers("/project2/", "/project2/login", "/project2/trainers")	//URL whitelist
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			//.httpBasic();
			.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/game", true) //TODO: change this to the actual game URL
			.and()
			.logout()
				.logoutUrl("/logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(ts);
		return provider;
	}
	
	
}*/
