package com.zee.zee5app.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.repository.impl.UserRepositoryImpl;

@Component
public class UserService2 {
	private UserRepository repository;// = UserRepositoryImpl.getInstance();
	//Service is consuming the repository
	

	private UserService2() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	//If we want to create that single object then we have to create it 
	//inside the same class 
	//and we have to share reference with others 
	// to do the same we have to declare a method
	
	private static UserService2 service = null;
	//This would be static 
	//only one copy
	
	public static UserService2 getInstance() throws IOException {
		//We can remove the dependency of the method on the object
		// Like getInstance method has dependency on userService object 
		//We can remove that by using static then it becomes object independent
		
		//UserService service = null;
		//this would be a local reference --> created during the execution of getInstance
		
		//Static method will only access only static reference
		if(service == null)
			service = new UserService2();
		return service;
		
	}
//	public String addUser(Register register) {
//		//Do we need to consume the addUser method from repo
//		return this.repository.addUser(register);
//		 
//	}
	public String addUser(Register register2) {
		// TODO Auto-generated method stub
		return this.repository.addUser(register2);
	}
	
	
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		return this.repository.getUserById(id);
	}
	
	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException {
		return this.repository.getAllUsers();
		
	}
}
