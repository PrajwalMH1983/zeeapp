package com.zee.zee5app.dto.repository;

import java.util.Optional;

import com.zee.zee5app.dto.Register;

public interface UserRepository3 {
	public String addUser(Register register);
	public String updateUser(String userId , Register register);
	public Optional<Register> getUserById(String userId);
	public Register[] getUsers();
	public String deleteUserById(String userId);
}

//For interfaces we cant create or declare the objects 
//We can declare only references
//When we will refer the objects whose class is implementing the interface 
//then that object will behave like an interface 
//that is we can get only access for the interface overridden methods