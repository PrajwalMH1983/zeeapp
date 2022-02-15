package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.User;
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
	public User addUser(User register) throws AlreadyExistsException {
		// TODO Auto-generated method stub
		
		boolean status = userRepository.existsByEmail(register.getEmail()) ;
		if(status)
			throw new AlreadyExistsException("This Record Already Exists");
		
		User register2 = userRepository.save(register);
		
		if(register2 != null) {
			Login login = new Login(register.getEmail(), register.getPassword() , register2);
			if(loginRepository.existsByUserName(register.getEmail()))
				throw new AlreadyExistsException("This Record Already Exists");
			String result = loginService.addCredentials(login);
			if(result.equals("Successful"))
				return register2;
			else {
//				//rollback here
				return null;
//			}
//			return register2;
			}
		}
		else
			return null;
	}

	@Override
	public User getUserById(Long userId)
			throws IdNotFoundException{
		// TODO Auto-generated method stub
		Optional<User> optional = userRepository.findById(userId);
		
		if(optional.isEmpty()) {
			throw new IdNotFoundException("Id does not exists");
		} else {
			return optional.get();
		}
		//return userRepository.findById(userId);
	}

	@Override
	public User[] getAllUsers() throws InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		List<User> list = userRepository.findAll();
		User[] registers = new User[list.size()];
		return list.toArray(registers);
	}

	@Override
	public Optional<List<User>> getAllUsersDetails() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userRepository.findAll());
	}

	@Override
	public String deleteUserById(Long userId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException {
		// TODO Auto-generated method stub
		User optional = this.getUserById(userId);
		if(optional == null)
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
