package com.zee.zee5app;

import java.math.BigDecimal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.service.UserService;

@SpringBootApplication
public class Zee5appspringboot2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Zee5appspringboot2Application.class,
				args);

		//UserRepository userRepository = applicationContext.getBean(UserRepository.class);
		
		UserService service = applicationContext.getBean(UserService.class);

		for(int i=1;i<=5;++i) {
			Register register;
			try {
				register = new Register("reg000" + i, "A" + i, "B" + i, "xyz" + i + "@abc.com", "hello123" + i, null);
				register.setContactNumber(new BigDecimal("9" + i +"9" + i + "7979797"));
				System.out.println(service.addUser(register));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		applicationContext.close();
	}

}
