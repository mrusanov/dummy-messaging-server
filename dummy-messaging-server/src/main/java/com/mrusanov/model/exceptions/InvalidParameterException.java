package com.mrusanov.model.exceptions;

public class InvalidParameterException extends Exception {

	private static final long serialVersionUID = 6806527665061596101L;

	public InvalidParameterException() {
		super();
	}

	public InvalidParameterException(String message) {
		super(message);
	}

	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}

}
