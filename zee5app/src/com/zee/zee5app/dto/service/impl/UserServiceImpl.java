package com.zee.zee5app.dto.service.impl;

import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.repository.UserRepository3;
import com.zee.zee5app.dto.repository.impl.UserRepositoryImpl;
import com.zee.zee5app.dto.service.UserService2;

public class UserServiceImpl implements UserService2 {

	
	private UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static UserService2 service;
	
	public static UserService2 getInstance() {
		if(service == null)
			service = new UserServiceImpl();
		
		return service;
	}
	
	UserRepository3 userRepository = UserRepositoryImpl.getInstance();
	
	//UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
	
	
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		return userRepository.addUser(register);
	}

	@Override
	public String updateUser(String userId, Register register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Register> getUserById(String userId) {
		// TODO Auto-generated method stub
		
		return userRepository.getUserById(userId);
	}

	@Override
	public Register[] getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.getUsers();
	}

	@Override
	public String deleteUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
