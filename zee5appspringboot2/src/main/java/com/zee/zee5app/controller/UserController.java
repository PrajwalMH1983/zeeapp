package com.zee.zee5app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.service.UserService;

@RestController	//Version 4 @ResponseBody @Controller
//When ur target is to deal with the REST Api
//then u have to set the RESPONSEand wherever we have to share the response that method
//must be marked with @ResponseBody
//If the class is having 1000 methods then we have to mark 1000 times
//to avoid this they merged controller with responseBody to form RestController

@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	//responsible for adding the information
	//Info will be shared by the client in terms of JSON object
	//we need to read that JSON object
	//This JSON object is available in RequestBody
	//RequestBody is responsible for storing the information provided
	//We need to read that requestBody 
	//We need to transform JSON Object to java object ====> will be done by RequestBody
	
	
	//We need to inform that when this method should be called
	//for that we have to specify the endpoint
	@PostMapping("/addUser")
	public String addUser(@RequestBody Register register) {
		
		//These things are expected for each and every REST API calls
		//1. It should store the received info into DB
//		try {
//			String result = userService.addUser(register);
//			return result;
//		} catch (AlreadyExistsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//2. validation
		//3. return the crispy info to the client
		//4. customization in error response
		//5.declaration of custom exception
		return register.toString();
	}
}
