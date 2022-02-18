package com.doctor.project;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<?> handleAnyException(Exception ex, WebRequest request){
		
		/*
		 * String errorMessageDescription = ex.getLocalizedMessage();
		 * if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		 */
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), new Date());
		return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(value = {DegreeEmptyException.class})
	public ResponseEntity<?> handleUserServiceException(DegreeEmptyException ex, WebRequest request){
		
		/*
		 * String errorMessageDescription = ex.getLocalizedMessage();
		 * if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		 */
		 
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), new Date());
		return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
