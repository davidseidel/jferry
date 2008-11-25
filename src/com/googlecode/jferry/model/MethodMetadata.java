package com.googlecode.jferry.model;

import java.util.List;

import javax.activation.MimeType;

/**
 * Metadata of a Method;
 * 
 * @author dseidel
 *
 */
public class MethodMetadata {
	
	private RequestType requestType;
	
	private String path;
	
	private List<MethodParameterMetadata> parameters;
	
	private Class<?> returnType;
	
	private MimeType consumedMimeType;

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<MethodParameterMetadata> getParameters() {
		return parameters;
	}

	public void setParameters(List<MethodParameterMetadata> parameters) {
		this.parameters = parameters;
	}

	public Class<?> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}

	public MimeType getConsumedMimeType() {
		return consumedMimeType;
	}

	public void setConsumedMimeType(MimeType consumedMimeType) {
		this.consumedMimeType = consumedMimeType;
	}
}
