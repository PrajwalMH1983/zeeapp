package com.zee.zee5app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login")
public class Login implements Comparable<Login>{
	
	@Id
	@Email
	private String userName;
	@NotBlank
	private String password;
	@NotBlank
	private String regId;
	@NotNull
	private ROLE role;
	
	
	@Override
	public int compareTo(Login o) {
		// TODO Auto-generated method stub
		return o.userName.compareTo(this.getUserName());
	}

}
