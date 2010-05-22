package com.googlecode.jferry;

import java.lang.reflect.Method;

import com.googlecode.jferry.invocation.IMethodInvocationProcessor;
import com.googlecode.jferry.invocation.IMethodInvocationProcessorFactory;
import com.googlecode.jferry.model.IMethodMetadataFactory;
import com.googlecode.jferry.model.InvocationContext;
import com.googlecode.jferry.model.MethodInvocationInput;
import com.googlecode.jferry.model.MethodMetadata;

public abstract class AbstractClientProxy implements ClientProxy {

	protected abstract IMethodInvocationProcessorFactory getMethodInvocationProcessorFactory();
	
	protected abstract InvocationContext createInvocationContext();
	
	protected abstract IMethodMetadataFactory getMethodMetadataFactory();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// create processor
		MethodMetadata methodMetaData = this.getMethodMetadataFactory().create(method);
		IMethodInvocationProcessor invocationProcessor = this.getMethodInvocationProcessorFactory().create(methodMetaData);

		// create processor input
		MethodInvocationInput input = new MethodInvocationInput();
		
		if(args != null) {
			input.setArguments(args);
		} else {
			input.setArguments(new Object[] {});
		}
		input.setContext(this.createInvocationContext());
		input.setMethodMetadata(methodMetaData);
		
		// do invocation
		Object result = invocationProcessor.invoke(input);
		
		return result;
	}

}
