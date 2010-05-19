package com.googlecode.jferry.invocation;

import com.googlecode.jferry.model.MethodInvocationInput;
import com.googlecode.jferry.model.RequestType;

/**
 * Processor to perform method invocations.
 * 
 * @author dseidel
 *
 */
public interface IMethodInvocationProcessor {
	
	/**
	 * Returns the request type, which can be handled by this processor.
	 * 
	 * @return the request type, which can be handled by this processor.
	 */
	RequestType requestType();
	
	/**
	 * Performs the method invocation, and returns the result.
	 * 
	 * @param input invocation input.
	 * @return result of the invocation
	 * @throws MethodInvocationException if the invocation fails, a MethodInvocationException is thrown
	 */
	Object invoke(MethodInvocationInput input) throws MethodInvocationException; 
}
