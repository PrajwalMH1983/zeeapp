package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.repository.impl.UserRepositoryImpl;
import com.zee.zee5app.service.UserService;

public class UserServiceImpl implements UserService {

	
	private UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	private static UserService service;
	
	public static UserService getInstance() {
		if(service == null)
			service = new UserServiceImpl();
		
		return service;
	}
	
	UserRepository userRepository = UserRepositoryImpl.getInstance();
	
	//UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
	
	
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		return userRepository.addUser(register);
	}

	@Override
	public String updateUser(String userId, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.updateUser(userId, register);
	}

	@Override
	public Optional<Register> getUserById(String userId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		
		return userRepository.getUserById(userId);
	}

	@Override
	public Register[] getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.getAllUsers();
	}

	@Override
	public String deleteUserById(String userId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.deleteUserById(userId);
	}

	@Override
	public List<Register> getAllUsersDetails() {
		// TODO Auto-generated method stub
		return userRepository.getAllUsersDetails();
	}

}
