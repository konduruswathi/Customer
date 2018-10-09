package com.capgemini.customer.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.customer.entities.ErrorMessage;
import com.capgemini.customer.exception.AuthenticationFailedException;
import com.capgemini.customer.exception.CustomerNotFoundException;

public class ExceptionController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<ErrorMessage> customerNotFoundException(CustomerNotFoundException customerNotFoundException,
			HttpServletRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(), customerNotFoundException.getMessage(),
				LocalDateTime.now(), HttpStatus.NOT_FOUND);
		log.error(errorMessage.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = AuthenticationFailedException.class)
	public ResponseEntity<ErrorMessage> authFailedException(AuthenticationFailedException customerNotFoundException,
			HttpServletRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(), customerNotFoundException.getMessage(),
				LocalDateTime.now(), HttpStatus.FORBIDDEN);
		log.info(errorMessage.toString());
		return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
	}
}