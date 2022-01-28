package com.zee.zee5app.dto.repository.impl;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.repository.UserRepository3;

public class UserRepositoryImpl implements UserRepository3 {

	//We need singleton object for Repository
	
	private Register[] registers = new Register[10];
	private static int count = -1;
	
	
	private UserRepositoryImpl(){
		
	}
	
	private static UserRepository3 repository;
	
	public static UserRepository3 getInstance() {
		if(repository == null)
			repository = new UserRepositoryImpl();
		return repository;
	}
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
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
		//return null;
	}

	@Override
	public String updateUser(String userId, Register register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register getUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Register[] getUsers() {
		// TODO Auto-generated method stub
		return registers;
	}

	@Override
	public String deleteUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
