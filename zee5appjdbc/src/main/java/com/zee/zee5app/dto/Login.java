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
	
	public Login(String userName, String password, String regId) {
		super();
		this.userName = userName;
		this.password = password;
		this.regId = regId;
	}
	
	
	@Override
	public int compareTo(Login o) {
		// TODO Auto-generated method stub
		return o.userName.compareTo(this.getUserName());
	}


}
