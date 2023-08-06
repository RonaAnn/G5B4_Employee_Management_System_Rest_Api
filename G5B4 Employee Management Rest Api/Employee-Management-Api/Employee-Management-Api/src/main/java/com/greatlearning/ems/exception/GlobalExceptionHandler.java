package com.greatlearning.ems.exception;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String invalidEmployeeId(NoSuchElementException exception) {

		return exception.getMessage();
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String validationException(MethodArgumentNotValidException exception) {

		return exception.getFieldErrors()
				 .stream()
				 .map(fieldError -> fieldError.getDefaultMessage())
				 .collect(Collectors.joining(";\n"));
	}
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String invalidUserName(UsernameNotFoundException exception) {
		
		return exception.getMessage();
	}
}
