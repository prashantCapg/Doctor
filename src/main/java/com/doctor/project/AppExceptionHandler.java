package com.doctor.project;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<?> handleAnyException(Exception ex){
		
		/*
		 * String errorMessageDescription = ex.getLocalizedMessage();
		 * if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		 */
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), new Date());
		return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(value = {DegreeEmptyException.class})
	public ResponseEntity<?> handleUserServiceException(DegreeEmptyException ex, WebRequest request){
		
		
		 
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), new Date());
		return new ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(value = {DegreeNotFoundException.class})
	public ResponseEntity<?> handleEmptyException(DegreeNotFoundException ex, WebRequest request){
		
		
		 
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), new Date());
		return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<List> processUnmergeException(final MethodArgumentNotValidException ex) {

       List list = ex.getBindingResult().getAllErrors().stream()
               .map(fieldError -> fieldError.getDefaultMessage())
               .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }
	
}
