package com.zee.zee5app.dto;

import java.util.Objects;

import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidNameException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

// Because of this @Data it implicitly manages all the setters and getters
@Data
@EqualsAndHashCode
//Why Comparable ?
//cuz comparable interface is providing compareTo method and this is responsible for
//performing comparison which is used for maintaining in sorted order
public class Register implements Comparable<Register>{
	
	//It should have min length of 6.
	//We have to write a code to validate the length and
	//then assign the value
	@Setter(value = AccessLevel.NONE)
	private String id;
	
	@Setter(value = AccessLevel.NONE)
	private String firstName;
	
	@Setter(value = AccessLevel.NONE)
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
	
	public void setId(String id) throws InvalidIdLengthException {
		//throws : it will provide the list of exceptions may be raised 
		//It will hold the list of checked exceptions
		if(id.length() < 6) {
			//It should raise the InvalidIdLengthException
			//Exception object is created by JVM 
			//user defined exception object must be raised by us
			throw new InvalidIdLengthException("Id length is less than or equal to 6.");
			//throw : it will throw the exception
		}
		this.id = id;
	}
	
	public void setFirstName(String firstName) throws InvalidNameException {
		
		if(firstName == null || firstName == "" || firstName.length() < 2) {
			throw new InvalidNameException("firstName is not valid");
		}
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) throws InvalidNameException {
		
		if(lastName == null || lastName == "" || lastName.length() < 2) {
			throw new InvalidNameException("lastname is not valid");
		}
		this.lastName = lastName;
	}

	public Register(String id, String firstName, String lastName, String email, String password) throws InvalidIdLengthException, InvalidNameException {
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.email = email;
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Register other = (Register) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	@Override
	public int compareTo(Register o) {
		// TODO Auto-generated method stub
		//return this.id.compareTo(o.getId());
		
		//For reverse order
		return o.id.compareTo(this.getId());
	}
	
	
	//Private stuff will be only accessible inside the class
	
	//Setter : is used to set the values for a particular field
	//Getter : to get / return the value of a specific field.
	
}
