package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.repository.impl.UserRepositoryImpl;
import com.zee.zee5app.service.UserService;

public class UserServiceImpl implements UserService {

	
	private UserServiceImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	private static UserService service;
	
	public static UserService getInstance() throws IOException {
		if(service == null)
			service = new UserServiceImpl();
		
		return service;
	}
	
	private UserRepository userRepository = UserRepositoryImpl.getInstance();
	
	//UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
	
	
	@Override
	public String addUser(Register register) {
		// TODO Auto-generated method stub
		return this.userRepository.addUser(register);
	}

	@Override
	public String updateUser(String userId, Register register) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.userRepository.updateUser(userId, register);
	}

	@Override
	public Optional<Register> getUserById(String userId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		
		return this.userRepository.getUserById(userId);
	}

	@Override
	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		return this.userRepository.getAllUsers();
	}

	@Override
	public String deleteUserById(String userId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.userRepository.deleteUserById(userId);
	}

	@Override
	public Optional<List<Register>> getAllUsersDetails() throws InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		return this.userRepository.getAllUsersDetails();
	}

	

}
