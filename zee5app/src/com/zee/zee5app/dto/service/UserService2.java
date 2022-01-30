package com.zee.zee5app.dto.service;

import java.util.Optional;

import com.zee.zee5app.dto.Register;

public interface UserService2 {
	public String addUser(Register register);
	public String updateUser(String userId , Register register);
	public Optional<Register> getUserById(String userId);
	public Register[] getAllUsers();
	public String deleteUserById(String userId);
}
