package org.wolf.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.wolf.web.config.login.LoginAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WolfWebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/resources/**","/dubbo/**","/login/**","/swagger*/**","/webjars/**","/v2/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler());
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("123456").roles("USER").and()
				.withUser("admin").password("123456").roles("USER", "ADMIN");
	}
	
	 @Bean  
	 public LoginAuthenticationSuccessHandler loginSuccessHandler(){  
		 return new LoginAuthenticationSuccessHandler();  
	 }
}
