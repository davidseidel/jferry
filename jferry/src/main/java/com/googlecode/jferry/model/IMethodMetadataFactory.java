package com.googlecode.jferry.model;

import java.lang.reflect.Method;

public interface IMethodMetadataFactory {
	/**
	 * 
	 * @param method
	 * @return
	 * @throws MetadataCreationException
	 */
	MethodMetadata create(Method method) throws MetadataCreationException;
}
