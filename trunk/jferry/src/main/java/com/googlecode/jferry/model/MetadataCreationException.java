package com.googlecode.jferry.model;

public class MetadataCreationException extends Exception {

	private static final long serialVersionUID = 1L;

	public MetadataCreationException() {
	}

	public MetadataCreationException(String message) {
		super(message);
	}

	public MetadataCreationException(Throwable cause) {
		super(cause);
	}

	public MetadataCreationException(String message, Throwable cause) {
		super(message, cause);
	}

}
