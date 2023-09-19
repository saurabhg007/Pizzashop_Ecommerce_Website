package com.Spring;

public class DisabledUserException extends Exception {

	private static final long serialVersionUID = 1L;

	public DisabledUserException() {}
	public DisabledUserException(String msg) {
		super(msg);
	}

}
