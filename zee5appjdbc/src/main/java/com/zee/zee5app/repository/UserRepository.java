package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;

public interface UserRepository {
	public String addUser(Register register);
	public String updateUser(String userId , Register register) throws IdNotFoundException;
	public Optional<Register> getUserById(String userId) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException;
	public Register[] getAllUsers() throws InvalidIdLengthException, InvalidNameException;
	public Optional<List<Register>> getAllUsersDetails() throws InvalidIdLengthException, InvalidNameException;
	public String deleteUserById(String userId) throws IdNotFoundException;
}

//For interfaces we cant create or declare the objects 
//We can declare only references
//When we will refer the objects whose class is implementing the interface 
//then that object will behave like an interface 
//that is we can get only access for the interface overridden methods