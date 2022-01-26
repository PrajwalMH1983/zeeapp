package com.zee.zee5app.dto;

import lombok.Data;

// Because of this @Data it implicitly manages all the setters and getters
@Data

public class Register {
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public Register()
	{
		//Explicit Default constructor
		//Any kind of customization during the initialization of object
		//then its better to introduce EDC or Public constructor or both as per the needi
		System.out.println();
	}
	//Private stuff will be only accessible inside the class
	
	//Setter : is used to set the values for a particular field
	//Getter : to get / return the value of a specific field.
	
}
