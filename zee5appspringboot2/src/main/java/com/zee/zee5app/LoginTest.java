package com.zee.zee5app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.service.LoginService;

@SpringBootApplication
public class LoginTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext applicationContext =
				SpringApplication.run(LoginTest.class, args);
		
		LoginService loginService = applicationContext.getBean(LoginService.class);
		System.out.println("LOGIN :");
		for (int i = 1; i <= 5; i++) {
			Login login = new Login("praj"+i+"@gmail.com", "hello12"+i,null);
			System.out.println(loginService.addCredentials(login) + " " + i);
		}
		System.out.println();
		
		System.out.println("DELETE LOGIN BY EMAILID");
		try {
			System.out.println(loginService.deleteCredentials("praj2@gmail.com"));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println();
		
		applicationContext.close();

	}

}
