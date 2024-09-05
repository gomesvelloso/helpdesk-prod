package com.example.demo.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.services.exceptions.DataIntegrityViolationException;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler //Manipulador de Exception
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
		
		StandardError error = new StandardError(System.currentTimeMillis(), 
												HttpStatus.NOT_FOUND.value(), 
												"Object not found", 
												ex.getMessage(), 
												request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler //Manipulador de Exception
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		
		StandardError error = new StandardError(System.currentTimeMillis(), 
												HttpStatus.BAD_REQUEST.value(), 
												"Violação de dados.", 
												ex.getMessage(), 
												request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler //Manipulador de Exception
	public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException  ex, HttpServletRequest request) {
		
		ValidationError errors = new ValidationError(System.currentTimeMillis(),
													HttpStatus.BAD_REQUEST.value(), 
													"Validation Error",
													"Error na validação dos campos",
													request.getRequestURI());
		
		for(FieldError x : ex.getBindingResult().getFieldErrors()) {
			errors.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}
