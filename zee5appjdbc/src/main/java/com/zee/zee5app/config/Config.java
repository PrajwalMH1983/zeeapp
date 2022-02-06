package com.zee.zee5app.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.zee.zee5app.utils.PasswordUtils;


@Configuration //It is used to mark on config class / classes
//here we will only hold all commonly required objects for our application

//we read this file content to establish database connection
@PropertySource("classpath:application.properties") //It is responsible to read the property file


//it helps spring / bean to search for the particular one or else it will have to search the whole repositories 
@ComponentScan("com.zee.zee5app")	//In this base package our main method is already defined

public class Config {
	
	//This impl object is prepared by spring
	@Autowired	//will bring already created objects based on the name (ref name)/ type
	Environment environment; //Is a reference and we need to bring it to implementation object
	//we need to inject that already created object 
	//we need to perform dependency injection
 
	
	//config : db related , reading prop file , commonly required beans (password encoder)
	//this method is responsible for providing the dataSource and this is responsible for managing the connections
	//we would be getting only 1 object 
	
	@Bean(name = "ds")  	//Is responsible for providing the singleton object ---> It is responsible for applying singleton DP for methods ,
	//it is a method level annotation
	
	//Here if u mention prototype we can have multiple objects 
	@Scope("singleton") 	//If u will call getBean method N no.of times then u will get N objects
	//To get multiple objects we should use prototype scope
	
	//If we will not specify the bean name then it will take or consider the method name as bean name 
	public DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		//here we have to provide the userName to connect
		basicDataSource.setUsername(environment.getProperty("jdbc.username"));
		basicDataSource.setPassword(environment.getProperty("jdbc.password"));
		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
		basicDataSource.setDefaultAutoCommit(false);
		
		return basicDataSource;
	}
	
	@Bean 	//we are going to create the object 
	//this object we can initialize as per the requirements
	//we can customize it as when required in case of bean
	//This would be better to use when we are using a lot of customizations
	
	public PasswordUtils passwordUtils() {
		return new PasswordUtils();
	}
}
