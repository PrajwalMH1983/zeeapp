package com.zee.zee5app.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class PathNotFoundException extends Exception {
	public PathNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
}
