package com.zee.zee5app.dto.repository.impl;

import java.util.ArrayList;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.repository.UserRepository3;

public class UserRepositoryImpl implements UserRepository3 {

	//We need singleton object for Repository
	private ArrayList<Register> arrayList = new ArrayList<>();
	//When u will use DC for AL then by default it will hold 10 elements
	//of type Register 
	//private Register[] registers = new Register[10];
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
//		if(count == registers.length - 1) {
//			//array is full or we should go for dynamically growing the size of array
//			Register temp[] = new Register[registers.length * 2];
//			
//			//We need to copy the contents from old to new
//			System.arraycopy(registers, 0, temp, 0 , registers.length);
//			
//			registers = temp;
//			
//			//We were not adding the newly created object 
//			//So the provided object is added into the array or not
//			registers[++count] = register;
//			
//			return "Success";
//		}
//		registers[++count] = register;
//		return "Success";
		//return null;
		
		
		boolean result = this.arrayList.add(register);
		if(result)
			return "Added a User";
		
		return "Could'nt add ";
	}

	@Override
	public String updateUser(String userId, Register register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//Optional its a class which is specifically designed for handling the NullPointerException
	public Optional<Register> getUserById(String userId) {
		// TODO Auto-generated method stub
		//We need to traverse the AL 
		
		for (Register register : arrayList) {
			if(register != null && register.getId().equals(userId)) {
				return Optional.of(register);	//We use Optional.of() only when we are sure that our program is gonna return something
			}

		}
		return Optional.empty();
		
//		Register register2 = null;
//		for (Register register : arrayList) {
//			if(register != null && register.getId().equals(userId)) {
//				register2 = register;
//			}
//
//		}
		
		//If register2 is holding null it will act like an empty 
		//If register2 is holding object it will act like of
		//return Optional.ofNullable(register2);
	}

	@Override
	public Register[] getUsers() {
		// TODO Auto-generated method stub
		//return registers;
		return null;
	}

	@Override
	public String deleteUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
