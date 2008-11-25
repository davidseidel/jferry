package com.googlecode.jferry.invocation;

import com.googlecode.jferry.model.MethodMetadata;

/**
 * Factory to create method invocation processors.
 * 
 * @author dseidel
 *
 */
public interface IMethodInvocationProcessorFactory {
	
	/**
	 * Returns a method invocation processor, which can perform an invocation
	 * of a method, which is described by the metadata.
	 * 
	 * @param methodMetaData
	 * @return  a method invocation processor
	 */
	IMethodInvocationProcessor create(MethodMetadata methodMetaData);
}
