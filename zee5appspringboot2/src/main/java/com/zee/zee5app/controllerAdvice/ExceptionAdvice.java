package com.zee.zee5app.controllerAdvice;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {
	//This class should be used when any user defined exception is called through out 
	//all the controller
	
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> alreadyRecordExistsExceptionHandler(AlreadyExistsException e) {
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Record Already Exists " + e.getMessage());
		return ResponseEntity.badRequest().body(map);
	}
	
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idNotFoundExceptionHandler(IdNotFoundException e) {
		HashMap<String, String> map = new HashMap<>();
		map.put("message", " " + e.getMessage());
		return ResponseEntity.badRequest().body(map);
	}
	
	
	@ExceptionHandler(Exception.class)		//If no match then it will act as a default exception handler
	public ResponseEntity<?> exceptionHandler(Exception e) {
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Unknown Exception " + e.getMessage());
		return ResponseEntity.badRequest().body(map);
	}
	
	
	
	
}
