package com.zee.zee5app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Login implements Comparable<Login>{
	private String userName;
	private String password;
	private String regId;
	private ROLE role;
	
	public Login(String userName, String password, String regId, ROLE role) {
		super();
		this.userName = userName;
		this.password = password;
		this.regId = regId;
		this.role = role;
	}
	
	
	@Override
	public int compareTo(Login o) {
		// TODO Auto-generated method stub
		return o.userName.compareTo(this.getUserName());
	}

}
