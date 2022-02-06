package com.zee.zee5app;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.UserRepository;

public class MainSpring {
	public static void main(String[] args) {
		//we need to establish or create spring environment
		//This one will kick start of your spring application
		//application context container
		//here we have to initialize the application context container
		//java based configurations
		//this config class is digested by your AnnotationConfigApplicationContext class
		//ApplicationContext is an interface
		//AnnotationConfigApplicationContext will form the concrete class
		//bean refers to object
		//If we have not overridden then by default it will follow from the object class
		// @Repository : We should mark to all our repository Impls 
		//@Service --> we should mark to all our service Impls  
		//@Component ---> commonly required classes that is Utility classes ----> all these 3 gives u the singleton object
		
		
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		System.out.println(userRepository);
		
		UserRepository userRepository2 = applicationContext.getBean(UserRepository.class);
		System.out.println(userRepository2);
		System.out.println(userRepository.hashCode());
		System.out.println(userRepository2.hashCode());
		System.out.println(userRepository.equals(userRepository2));
		
		DataSource dataSource = applicationContext.getBean("dataSource" , DataSource.class);
		System.out.println(dataSource != null);
		
		Register register = null;
		try {
			register = new Register("pmh0018", "A2", "B2", "xyz2@abc.com", "hello1234" , null);
			register.setContactNumber(new BigDecimal("9797979797"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println(userRepository.addUser(register));
		applicationContext.close();
	}
}
