package com.googlecode.jferry.invocation.commonshttpclient;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.jferry.invocation.IMethodInvocationProcessor;
import com.googlecode.jferry.invocation.IMethodInvocationProcessorFactory;
import com.googlecode.jferry.model.MethodMetadata;

public class MethodInvocationProcessorFactory implements
		IMethodInvocationProcessorFactory {
	
	private List<IMethodInvocationProcessor> processors;
	
	public MethodInvocationProcessorFactory() {
		this.processors = new ArrayList<IMethodInvocationProcessor>();
		this.processors.add(new PostMethodInvocationProcessor());
		this.processors.add(new PutMethodInvocationProcessor());
		this.processors.add(new GetMethodInvocationProcessor());
		this.processors.add(new DeleteMethodInvocationProcessor());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMethodInvocationProcessor create(MethodMetadata methodMetaData) {
		IMethodInvocationProcessor processor = null;
		for(IMethodInvocationProcessor processorToTest : this.processors) {
			if(processorToTest.requestType().equals(methodMetaData.getRequestType())) {
				processor = processorToTest;
				break;
			}
		}

		return processor;
	}

	public List<IMethodInvocationProcessor> getProcessors() {
		return processors;
	}

	public void setProcessors(List<IMethodInvocationProcessor> processors) {
		this.processors = processors;
	}

}
