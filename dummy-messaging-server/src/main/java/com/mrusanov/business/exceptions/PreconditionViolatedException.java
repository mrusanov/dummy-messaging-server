package com.mrusanov.business.exceptions;

public class PreconditionViolatedException extends Exception {

	private static final long serialVersionUID = -8474507394108858067L;

	public PreconditionViolatedException() {
		super();
	}

	public PreconditionViolatedException(String message) {
		super(message);
	}

	public PreconditionViolatedException(String message, Throwable cause) {
		super(message, cause);
	}

}
