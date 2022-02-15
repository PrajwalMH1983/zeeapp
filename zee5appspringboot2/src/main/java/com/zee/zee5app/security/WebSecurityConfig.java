package com.zee.zee5app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//In case of spring boot configuration class specifications will be done internally by spring
@Configuration
//It will have only security related configurations
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)	//we can confirm the accessibility for our users whether the particular user 
//is able to access our rest controllers with security or without security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

}
