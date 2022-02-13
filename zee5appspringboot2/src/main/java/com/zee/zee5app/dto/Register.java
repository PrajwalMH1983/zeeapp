package com.zee.zee5app.dto;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zee.zee5app.utils.CustomListSerializer;

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
	@Column(name = "regId")	//Specifies the column name as regId in the table
	@Length(min = 6)
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
	
	@NotNull
	private BigInteger contactNumber;
	
	
	//Many roles will be allocated to many users 
	//and many users can have multiple roles
	@ManyToMany
	//@JsonIgnore
	//we want to mention this relationship into 3rd table
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "regId"),
	inverseJoinColumns = @JoinColumn(name = "roleId"))//maintain the relationship between 
	//InverseJoin is for role table and the join column is for the register table
	//registered user(regId) and role(roleId)
	private Set<Role> roles = new HashSet<>();
	
	@OneToOne(mappedBy = "register" , cascade = CascadeType.ALL)
    //@OneToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JsonSerialize(using = CustomListSerializer.class)
    @JoinColumn(name = "regId")
    //@JsonProperty(access=Access.WRITE_ONLY)
	@JsonIgnore
	private Login login;
	
	
	@OneToOne(mappedBy = "register" , cascade = CascadeType.ALL)
	private Subscription subscription;
	
	@Override
	public int compareTo(Register o) {
		// TODO Auto-generated method stub
		//return this.id.compareTo(o.getId());
		
		//For reverse order
		return this.id.compareTo(o.getId());
	}





	





	




	
	
	//Private stuff will be only accessible inside the class
	
	//Setter : is used to set the values for a particular field
	//Getter : to get / return the value of a specific field.
	
}
