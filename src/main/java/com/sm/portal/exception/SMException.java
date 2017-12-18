package com.sm.portal.exception;

public class SMException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SMException() {
	}

	public SMException(Throwable throwable) {
		super(throwable);
	}

	public SMException(String message) {
		super(message);
	}

	public SMException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
