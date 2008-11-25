package com.googlecode.jferry.invocation.request;

import javax.activation.MimeType;

import com.googlecode.jferry.model.MethodInvocationInput;

/**
 * This converter can convert the arguments of a method invocation to a request
 * body.
 * 
 * @author dseidel
 * 
 */
public interface IRequestBodyConverter {
	/**
	 * Converts the entity parameter of a method invocation to a request body.
	 * 
	 * @param input
	 *            method invocation input
	 * @return request body
	 */
	String convert(MethodInvocationInput input);

	/**
	 * This method checks if the converter can perfom a conversion for a certain
	 * target mime type.
	 * 
	 * @param type
	 *            target mime type of the convertion.
	 * @return true = the converter can perfom the conversion, false = the
	 *         converter can not perform the conversion
	 */
	boolean canConvert(MimeType type);
}
