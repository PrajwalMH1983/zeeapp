package com.zee.zee5app.dto.service;

import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.repository.UserRepository;
import com.zee.zee5app.dto.repository.UserRepository3;
//Service objects are used to just call the services inside the main method
//can we do this using 1 object
//To fix this problem -- > they provided a solution called singleton design pattern --> one object for the specific type
import com.zee.zee5app.dto.repository.impl.UserRepositoryImpl;

public class UserService {
	private UserRepository3 repository = UserRepositoryImpl.getInstance();
	//Service is consuming the repository
	
	private UserService() {
		// TODO Auto-generated constructor stub
	}
	
	//If we want to create that single object then we have to create it 
	//inside the same class 
	//and we have to share reference with others 
	// to do the same we have to declare a method
	
	private static UserService service = null;
	//This would be static 
	//only one copy
	
	public static UserService getInstance() {
		//We can remove the dependency of the method on the object
		// Like getInstance method has dependency on userService object 
		//We can remove that by using static then it becomes object independent
		
		//UserService service = null;
		//this would be a local reference --> created during the execution of getInstance
		
		//Static method will only access only static reference
		if(service == null)
			service = new UserService();
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
	
	
	public Optional<Register> getUserById(String id) {
		return this.repository.getUserById(id);
	}
	
	public Register[] getUsers() {
		return this.repository.getUsers();
		
	}
}
