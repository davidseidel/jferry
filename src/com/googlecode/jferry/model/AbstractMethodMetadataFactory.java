package com.googlecode.jferry.model;

import java.lang.reflect.Method;
import java.util.List;

import javax.activation.MimeType;


public abstract class AbstractMethodMetadataFactory implements IMethodMetadataFactory {

	protected abstract String lookupPath(Method method);
	
	protected abstract RequestType lookupRequestType(Method method);
	
	protected abstract List<MethodParameterMetadata> createParameterList(Method method);
	
	protected abstract MimeType lookupConsumedMimeType(Method method);
	
	@Override
	public MethodMetadata create(Method method) {
		MethodMetadata metaData = new MethodMetadata();
		
		// get request type
		RequestType requestType = this.lookupRequestType(method);
		metaData.setRequestType(requestType);
		
		// get path
		String path = this.lookupPath(method);
		metaData.setPath(path);
		
		// set return type
		Class<?> returnType = method.getReturnType();
		metaData.setReturnType(returnType);
		
		// set parameters
		List<MethodParameterMetadata> parameters = this.createParameterList(method);
		metaData.setParameters(parameters);
		
		// set consumed mime type
		MimeType consumedMimeType = this.lookupConsumedMimeType(method);
		metaData.setConsumedMimeType(consumedMimeType);
		
		return metaData;
	}
}
