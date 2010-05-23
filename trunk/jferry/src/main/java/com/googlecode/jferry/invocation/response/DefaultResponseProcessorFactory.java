package com.googlecode.jferry.invocation.response;

import java.util.HashSet;
import java.util.Set;

import javax.activation.MimeType;

public class DefaultResponseProcessorFactory implements
		IResponseProcessorFactory {
	
	private Set<IResponseProcessor> responseProcessors;
	
	public DefaultResponseProcessorFactory() {
		responseProcessors = new HashSet<IResponseProcessor>();
		responseProcessors.add(new TextXmlResponseProcessor());
		responseProcessors.add(new TextPlainResponseProcessor());
		responseProcessors.add(new JsonResponseProcessor());
	}

	@Override
	public IResponseProcessor create(MimeType type) {
		IResponseProcessor processor = null;
		
		for(IResponseProcessor processorToTest : this.responseProcessors) {
			if(processorToTest.canProcess(type)) {
				processor = processorToTest;
				break;
			}
		}
		
		if(processor != null) {
			return processor;
		}
		
		throw new IllegalStateException("No processor found for " + type);
	}

	public Set<IResponseProcessor> getResponseProcessors() {
		return responseProcessors;
	}

	public void setResponseProcessors(Set<IResponseProcessor> responseProcessors) {
		this.responseProcessors = responseProcessors;
	}
	
}
