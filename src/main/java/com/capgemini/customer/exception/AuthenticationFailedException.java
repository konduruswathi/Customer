package com.capgemini.customer.exception;

public class AuthenticationFailedException extends RuntimeException {
public AuthenticationFailedException(String message) {
super(message);
}
}
