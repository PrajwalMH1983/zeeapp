package com.zee.zee5app.dto.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;

public interface UserService {
	public String addUser(Register register);
	public String updateUser(String userId , Register register) throws IdNotFoundException;
	public Optional<Register> getUserById(String userId) throws IdNotFoundException;
	public Register[] getAllUsers();
	public List<Register> getAllUsersDetails();
	public String deleteUserById(String userId) throws IdNotFoundException;
}
