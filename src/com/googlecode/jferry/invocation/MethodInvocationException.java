package com.googlecode.jferry.invocation;

/**
 * This exception is thrown, if an invocation of a method fails.
 * 
 * @author dseidel
 *
 */
public class MethodInvocationException extends Exception {
	private static final long serialVersionUID = 1L;

	public MethodInvocationException() {
		super();
	}

	public MethodInvocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodInvocationException(String message) {
		super(message);
	}

	public MethodInvocationException(Throwable cause) {
		super(cause);
	}

}
