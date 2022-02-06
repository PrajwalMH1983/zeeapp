package com.zee.zee5app.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration //It is used to mark on config class / classes
//here we will only hold all commonly required objects for our application

//we read this file content to establish database connection
@PropertySource("classpath:application.properties") //It is responsible to read the property file


//it helps spring / bean to search for the particular one or else it will have to search the whole repositories 
@ComponentScan("com.zee.zee5app")

public class Config {
	
	//This impl object is prepared by spring
	@Autowired	//will bring already created objects based on the name (ref name)/ type
	Environment environment; //Is a reference and we need to bring it to implementation object
	//we need to inject that already created object 
	//we need to perform dependency injection
 
	
	//config : db related , reading prop file , commonly required beans (password encoder)
	//this method is responsible for providing the dataSource and this is responsible for managing the connections
	//we would be getting only 1 object 
	
	@Bean  	//Is responsible for providing the singleton object ---> It is responsible for applying singleton DP for methods ,
	//it is a method level annotation
	
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
}
