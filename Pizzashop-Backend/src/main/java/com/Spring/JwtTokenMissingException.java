package com.Spring;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMissingException extends Exception {

	private static final long serialVersionUID = 1L;
public JwtTokenMissingException() {}
	public JwtTokenMissingException(String msg) {
		super(msg);
	}

}
