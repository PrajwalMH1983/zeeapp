package com.zee.zee5app;

import java.math.BigDecimal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.repository.UserRepository;

@SpringBootApplication
public class Zee5appspringboot2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Zee5appspringboot2Application.class,
				args);

		UserRepository userRepository = applicationContext.getBean(UserRepository.class);

		Register register = null;
		try {
			register = new Register("pmh0051", "A12", "B12", "xyz12@abc.com", "hello1234", null);
			register.setContactNumber(new BigDecimal("9797979797"));
			System.out.println(userRepository.addUser(register));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		applicationContext.close();
	}

}
