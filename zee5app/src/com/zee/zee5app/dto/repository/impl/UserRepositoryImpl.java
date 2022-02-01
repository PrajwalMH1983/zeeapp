package com.zee.zee5app.dto.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.repository.UserRepository;
import com.zee.zee5app.exception.IdNotFoundException;

public class UserRepositoryImpl implements UserRepository {

	//We need singleton object for Repository
	//As list is parent for linked list
	//A parent reference can handle the object of child
	//ArrayList and LinkedList both are referring to the same parent
	
	//private List<Register> arrayList = new LinkedList<>();
	private TreeSet<Register> set = new TreeSet<>();
	//private Set<Register> set = new LinkedHashSet<>();
	//HashSet by default holds 16 elements
	private Set<Register> arrayList = new HashSet<>();
	
	
	//When u will use DC for AL then by default it will hold 10 elements
	//of type Register 
	//private Register[] registers = new Register[10];
	private static int count = -1;
	
	
	private UserRepositoryImpl(){
		
	}
	
	private static UserRepository repository;
	
	public static UserRepository getInstance() {
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
		System.out.println(this.arrayList.size());
		if(result)
			return "Added a User";
		
		return "Could'nt add ";
	}

	@Override
	public String updateUser(String userId, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Register> optional = this.getUserById(userId);
		if(optional.isPresent()) {
			boolean result = set.remove(optional.get());
			set.add(register);
			if(result) {
				return "Updated User";
			} else {
				return "Could'nt update User";
			}
		}
		return "Failed To Update User";
	}

	@Override
	public Optional<Register> getUserById(String userId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Register register2 = null;
		for (Register register : arrayList) {
			if(register.getId().equals(userId)) {
				register2 = register;
				break;
			}
		}
		return Optional.of(Optional.ofNullable(register2)
				.orElseThrow(()-> new IdNotFoundException("Id Not Found Message")));
	}
	
//	@Override
//	//Optional its a class which is specifically designed for handling the NullPointerException
//	public Register getUserById(String userId) throws IdNotFoundException{// throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		//We need to traverse the AL 
//		Register register2 = null;
//		for (Register register : arrayList) {
//			if(register != null && register.getId().equals(userId)) {
//				//return Optional.of(register);	//We use Optional.of() only when we are sure that our program is gonna return something
//				register2 = register;
//			}
//
//		}
//		if(register2 == null) {
//			throw new IdNotFoundException("Id Not Found");
//		} else {
//			return register2;
//		}
//		//return Optional.empty();
//		
////		Register register2 = null;
////		for (Register register : arrayList) {
////			if(register != null && register.getId().equals(userId)) {
////				register2 = register;
////			}
////
////		}
//		
//		//If register2 is holding null it will act like an empty 
//		//If register2 is holding object it will act like of
////		return Optional.ofNullable(Optional
////				.of(register2)
////				.orElseThrow(()->new IdNotFoundException("Id Not Found")));
//	}

	@Override
	public Register[] getAllUsers() {
		// TODO Auto-generated method stub
		//return registers;
		Register register[] = new Register[arrayList.size()];
		return arrayList.toArray(register);
	}

	@Override
	public String deleteUserById(String userId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Register> optional = this.getUserById(userId);
		
		if(optional.isPresent()) {
			//Removal
			boolean result = arrayList.remove(optional.get());
			if(result) {
				return "Sucessful";
			} else {
				return "Failure";
			}
		} else {
			//Throw IdNotFoundException 
			throw new IdNotFoundException("Id Not Found Exception");
		}
	}
	
	@Override
	public List<Register> getAllUsersDetails() {
		// TODO Auto-generated method stub
		//We are returning all the details but we need to return it in terms of sorted order
		//To convert the set to list
		//Collections.sort(arrayList);
		//return arrayList;
		
		return new ArrayList<>(set.descendingSet());
	}
	

}
