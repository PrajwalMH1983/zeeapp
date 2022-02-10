package com.zee.zee5app.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Because of this @Data it implicitly manages all the setters and getters
@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
//Why Comparable ?
//cuz comparable interface is providing compareTo method and this is responsible for
//performing comparison which is used for maintaining in sorted order

//ORM Mapping purpose
@Entity		//Entity class is used for ORM
//we can customize the table name 

@Table(name = "register")

public class Register implements Comparable<Register>{
	
	//It should have min length of 6.
	//We have to write a code to validate the length and
	//then assign the value
	//All camel case is converted to snake case
	@Id		//JPA will consider this column as Primary key
	@Column(name = "regId")		//Specifies the column name as regId in the table
	private String id;
	
	@Size(max = 70)
	@NotBlank
	private String firstName;
	
	@Size(max = 70)
	@NotBlank
	private String lastName;
	
	@Size(max = 80)
	@Email
	private String email;
	
	@Size(max = 100)
	@NotBlank
	private String password;
	
	private BigDecimal contactNumber;
	

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
