package com.zee.zee5app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
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
	
	
	//Here so now it will throw exception to spring so it will handle that now
	//We use @Valid because it would be better to validate the data before getting it into the DB rather than validating at the DB
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@Valid @RequestBody Register register) throws AlreadyExistsException {
		
		//These things are expected for each and every REST API calls
		//1. It should store the received info into DB
//		try {
			Register result = userService.addUser(register);
			System.out.println(result);
			return ResponseEntity.status(201).body(result);
			
//		} catch (AlreadyExistsException e) {
//			// TODO Auto-generated catch block
//			//Return a json object with message : Record already exists
//			//Status : Problem
//			
//			Map<String, String> hashMap = new HashMap<>();
//			
//			hashMap.put("message", "Record Already exists");
//			
//			//Intead of providing the status(400) we have provided badRequest()
//			return ResponseEntity.badRequest().body(hashMap);
//		}
		//2. validation
		//3. return the crispy info to the client
		//4. customization in error response
		//5.declaration of custom exception

		
		//here we are supposed to return a json object with status problem 
	}
	
	
	
	//1. Retrieve a specific record
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String id) throws IdNotFoundException{
		Register register = userService.getUserById(id);
		return ResponseEntity.ok(register);
	}
	
	//2. Retrieving all records
	@GetMapping("/all")
	public ResponseEntity<?> getAllUsers() {
		Optional<List<Register>> optional = userService.getAllUsersDetails();
		if(optional.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("message" , "No Record Found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(optional.get());
	}
	
	//3. page wise records
}
