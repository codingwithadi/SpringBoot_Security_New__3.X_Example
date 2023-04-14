package com.codingwithadi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //this is for to perform pre authorization method
public class SecurityConfig {

	//authentication
	//userDeails
	@Bean
	public UserDetailsService detailsService() {
//		HardCoded values
		
//		UserDetails user = User.withUsername("user")
//				.password(encoder.encode("pass"))
//				.roles("USER")
//				.build();
//		UserDetails admin = User.withUsername("admin")
//				.password(encoder.encode("pass"))
//				.roles("ADMIN")
//				.build();
		
		return new UserInfoUserDetailsService();
				
	}
	
	//authorization
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {
		return http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("v1/api/public","v1/api/new").permitAll()
		.and()
		.authorizeHttpRequests()
		.requestMatchers("v1/api/**").authenticated()
		.and()
		.formLogin()
		.and()
		.build();
	}
	
	//passwordEncoder
	@Bean
	public PasswordEncoder passEncode() {
		return new BCryptPasswordEncoder();
	}
	
	//Authentication provider for login
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(detailsService());
		authenticationProvider.setPasswordEncoder(passEncode());
		return authenticationProvider;
	}
}
