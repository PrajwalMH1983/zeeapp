package com.zee.zee5app.service.impl;

import java.io.IOException;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.service.LoginService;

public class LoginServiceImpl implements LoginService {

	private LoginRepository loginRepository;
	
	public LoginServiceImpl() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String addCredentials(Login login) {
		// TODO Auto-generated method stub
		return loginRepository.addCredentials(login);
	}

	@Override
	public String deleteCredentials(String userName) {
		// TODO Auto-generated method stub
		return loginRepository.deleteCredentials(userName);
	}

	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		return loginRepository.changePassword(userName, password);
	}

}
