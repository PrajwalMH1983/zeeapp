package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;

public interface UserService {
	public Register addUser(Register register) throws AlreadyExistsException;
	public Register getUserById(String userId) throws IdNotFoundException;
	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException;
	public Optional<List<Register>> getAllUsersDetails();
	public String deleteUserById(String userId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException;
}
