package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;

public interface UserService {
	public String addUser(Register register);
	public String updateUser(String userId , Register register) throws IdNotFoundException;
	public Optional<Register> getUserById(String userId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException;
	public Register[] getAllUsers();
	public List<Register> getAllUsersDetails();
	public String deleteUserById(String userId) throws IdNotFoundException;
}
