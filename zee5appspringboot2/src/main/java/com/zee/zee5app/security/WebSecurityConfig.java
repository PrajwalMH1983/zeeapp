package com.zee.zee5app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zee.zee5app.security.jwt.AuthEntryPointJwt;
import com.zee.zee5app.security.jwt.AuthTokenFilter;
import com.zee.zee5app.security.services.UserDetailsServiceImpl;

//In case of spring boot configuration class specifications will be done internally by spring
@Configuration
//It will have only security related configurations
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)	//we can confirm the accessibility for our users whether the particular user 
//is able to access our rest controllers with security or without security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	//bean will help u to create singleton object
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		// TODO Auto-generated method stub
		//Encoding and decoding will be taken care by passwordEncoder implicitly
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	//For accessibility of endpoint
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
	    //to enable cross origin domain cors is used ... Cross-Site Request Forgery (CSRF)
		//cors : cross origin support : one domain can talk to another domain : for cross domain support
		//and() : we do chaining : help us to call other methods
		//csrf : cross sight request forgery : used for authentication
		//we disable() the csrf : as we are dealing with REST API so we get multiple requests 
		//without token we are not able to access any endpoints or any resources and that
		//is done by authorizeHttpRequests() and antMatchers() 
		
		http.cors().and().csrf().disable()
	      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
	      .antMatchers("/api/test/**").permitAll()
	      .anyRequest().authenticated();	//Means permit any request after authentication only
		
		 http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);		
	}
}
