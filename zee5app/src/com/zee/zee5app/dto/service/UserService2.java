package com.zee.zee5app.dto.service;

import com.zee.zee5app.dto.Register;

public interface UserService2 {
	public String addUser(Register register);
	public String updateUser(String userId , Register register);
	public Register getUserById(String userId);
	public Register[] getUsers();
	public String deleteUserById(String userId);
}
