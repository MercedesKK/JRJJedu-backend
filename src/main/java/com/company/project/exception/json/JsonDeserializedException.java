package com.company.project.exception.json;

public class JsonDeserializedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JsonDeserializedException(String message) {
		super(message);
	}

	public JsonDeserializedException(Throwable cause) {
		super(cause);
	}

	public JsonDeserializedException(String message, Throwable cause) {
		super(message, cause);
	}

}
