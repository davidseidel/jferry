package com.googlecode.jferry.model;

public class MethodInvocationInput {
	private InvocationContext context;
	
	private Object[] arguments;
	
	private MethodMetadata methodMetadata;

	public InvocationContext getContext() {
		return context;
	}

	public void setContext(InvocationContext context) {
		this.context = context;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public MethodMetadata getMethodMetadata() {
		return methodMetadata;
	}

	public void setMethodMetadata(MethodMetadata methodMetadata) {
		this.methodMetadata = methodMetadata;
	}
}
