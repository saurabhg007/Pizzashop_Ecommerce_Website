package com.Spring;

public class InvalidUserCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;
	public InvalidUserCredentialsException() {}
	public InvalidUserCredentialsException(String msg) {
		super(msg);
	}

}
