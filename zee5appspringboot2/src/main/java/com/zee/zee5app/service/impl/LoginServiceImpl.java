package com.zee.zee5app.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;// = LoginRepositoryImpl.getInstance();
	
	public LoginServiceImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	//private static LoginService service;
//	public static LoginService getInstance() throws IOException {
//		if(service == null)
//			service = new LoginServiceImpl();
//		return service;
//	}
	@Override
	public String addCredentials(Login login) {
		// TODO Auto-generated method stub
		Login login2 = loginRepository.save(login);
		if(login2 != null)
			return "Successful";
		return "Failed";
	}

	@Override
	public String deleteCredentials(String userName) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Login> optional = loginRepository.findById(userName);
		if(optional.isEmpty())
			throw new IdNotFoundException("Record Not Found");
		else {
			loginRepository.deleteById(userName);
			return "Successful";
		}
 	}

	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeRole(String userName, ROLE role) {
		// TODO Auto-generated method stub
		return null;
	}

}
