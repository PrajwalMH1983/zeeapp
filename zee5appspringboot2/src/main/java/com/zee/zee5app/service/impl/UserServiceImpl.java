package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.LoginService;
import com.zee.zee5app.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	
	//bring the userRepository object 
	//using getInstance method
	//are we expecting that repo object we should get it from spring 
	@Autowired
	private UserRepository userRepository;// = UserRepositoryImpl.getInstance();
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private LoginRepository loginRepository;
	
//	public UserServiceImpl() throws IOException {
//		// TODO Auto-generated constructor stub
//		
//	}
	
	
	//private static UserService userService;
//	public static UserService getInstance() throws IOException {
//		if(userService==null)
//			userService = new UserServiceImpl();
//		return userService;
//	}
	
	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = AlreadyExistsException.class)
	public String addUser(Register register) throws AlreadyExistsException {
		// TODO Auto-generated method stub
		
		if(userRepository.existsByEmailAndContactNumber(register.getEmail(), register.getContactNumber()))
			throw new AlreadyExistsException("This Record Already Exists");
		
		Register register2 = userRepository.save(register);
		
		if(register2 != null) {
			Login login = new Login(register2.getEmail(), register2.getPassword(), register2.getId());
			if(loginRepository.existsByUserName(login.getUserName()))
				throw new AlreadyExistsException("This Record Already Exists");
			String result = loginService.addCredentials(login);
			if(result.equals("Successful"))
				return "Successfull";
			else {
				//rollback here
				return "Failed";
			}
			//return "Successful";
		}
		else
			return "Failed";
	}

	@Override
	public Optional<Register> getUserById(String userId)
			throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}

	@Override
	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		List<Register> list = userRepository.findAll();
		Register[] registers = new Register[list.size()];
		return list.toArray(registers);
	}

	@Override
	public Optional<List<Register>> getAllUsersDetails() throws InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepository.findAll());
	}

	@Override
	public String deleteUserById(String userId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		Optional<Register> optional = this.getUserById(userId);
		if(optional.isEmpty())
			throw new IdNotFoundException("Record not Found");
		else {
			userRepository.deleteById(userId);
			return "Successful";
		}
	}

	

	
//	private UserServiceImpl() throws IOException{
//		// TODO Auto-generated constructor stub
//	}
//	
//	private static UserService service;
//	
//	public static UserService getInstance() throws IOException {
//		if(service == null)
//			service = new UserServiceImpl();
//		
//		return service;
//	}
//	
//	private UserRepository userRepository = UserRepositoryImpl.getInstance();
//	
//	//UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
//	
//	
//	@Override
//	public String addUser(Register register) {
//		// TODO Auto-generated method stub
//		return this.userRepository.addUser(register);
//	}
//
//	@Override
//	public String updateUser(String userId, Register register) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return this.userRepository.updateUser(userId, register);
//	}
//
//	@Override
//	public Optional<Register> getUserById(String userId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
//		// TODO Auto-generated method stub
//		
//		return this.userRepository.getUserById(userId);
//	}
//
//	@Override
//	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException {
//		// TODO Auto-generated method stub
//		return this.userRepository.getAllUsers();
//	}
//
//	@Override
//	public String deleteUserById(String userId) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		return this.userRepository.deleteUserById(userId);
//	}
//
//	@Override
//	public Optional<List<Register>> getAllUsersDetails() throws InvalidIdLengthException, InvalidNameException {
//		// TODO Auto-generated method stub
//		return this.userRepository.getAllUsersDetails();
//	}

	

}
