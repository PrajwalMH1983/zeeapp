package com.zee.zee5app.controllerAdvice;

import java.util.HashMap;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zee.zee5app.exception.AlreadyExistsException;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.apierror.ApiError;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler{
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
	
	//@Vaild should be customized
	//here we need 
	//1. custom error details object with suberror (field error)
	//2. create the beans
	//3. prepare the list and methods
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("validation Error");
		apiError.addValidationError1(ex.getBindingResult().getFieldErrors());
		apiError.addValidationErrors(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(apiError);
	}
	
	//Every time it is responsible for building the responseEntity Object
	//will help you to get responseEntity Object
	//if we want to make any changes into our existing object then in every return we have to do the change
	//instead of that if we will use this buildResponseEntity method we can achieve EaseOfMaintenance
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError , apiError.getHttpStatus());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<?> handleConstraintViolation() {
		return null;
	}
}
