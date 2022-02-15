package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;

public interface UserService {
	public User addUser(User register) throws AlreadyExistsException;
	public User getUserById(Long userId) throws IdNotFoundException;
	public User[] getAllUsers() throws InvalidIdLengthException, InvalidNameException;
	public Optional<List<User>> getAllUsersDetails();
	public String deleteUserById(Long userId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException;
}
