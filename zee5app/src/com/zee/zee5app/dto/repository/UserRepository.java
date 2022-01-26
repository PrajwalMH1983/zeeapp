package com.zee.zee5app.dto.repository;

import com.zee.zee5app.dto.Register;
//repo.objects are used to call only repo methods


public class UserRepository {
	private Register[] registers = new Register[10];
	private static int count = -1;
	
	private UserRepository() {
		// TODO Auto-generated constructor stub
		
	}
	
	//Gets us all the details of all the users in an array
	public Register[] getUsers() {
		return registers;
	}
	
	
	//Updating user is an assignment
	public String updateUser(String id , Register register) {
		
		return null;
	}
	
	//Delete user is an assignment
	public String deleteUser(String id) {
		
		return null;
	}
	//This method should return the user details based on the id
	public Register getUserById (String id) {
		//We need to traverse the array 
		
		for (Register register : registers) {
			if(register != null && register.getId().equals(id) ) {
				return register;
			}
		}
		
		return null;
		
	}
	
	
	//Add a new user
	public String addUser(Register register) {
		
		//registers.length ===> gives us the availability
		if(count == registers.length - 1) {
			//array is full or we should go for dynamically growing the size of array
			Register temp[] = new Register[registers.length * 2];
			
			//We need to copy the contents from old to new
			System.arraycopy(registers, 0, temp, 0 , registers.length);
			
			registers = temp;
			
			//We were not adding the newly created object 
			//So the provided object is added into the array or not
			registers[++count] = register;
			
			return "Success";
		}
		registers[++count] = register;
		return "Success";
	}
	
	
	private static UserRepository userRepository;
	public static UserRepository getInstance() {
		if(userRepository == null)
			userRepository = new UserRepository();
		return userRepository;
	}
}
