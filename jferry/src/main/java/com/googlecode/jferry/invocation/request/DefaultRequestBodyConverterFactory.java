package com.googlecode.jferry.invocation.request;

import java.util.ArrayList;
import java.util.List;

import javax.activation.MimeType;

public class DefaultRequestBodyConverterFactory implements IRequestBodyConverterFactory {

	private List<IRequestBodyConverter> requestBodyConverters;
	
	public DefaultRequestBodyConverterFactory() {
		this.requestBodyConverters = new ArrayList<IRequestBodyConverter>();
		this.requestBodyConverters.add(new XmlRequestBodyConverter());
		this.requestBodyConverters.add(new JsonRequestBodyConverter());
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IRequestBodyConverter create(MimeType type) {
		IRequestBodyConverter converter = null;
		
		for(IRequestBodyConverter requestBodyConverterToTest : this.requestBodyConverters) {
			if(requestBodyConverterToTest.canConvert(type)) {
				converter = requestBodyConverterToTest;
				break;
			}
 		}
		
		return converter;
	}
	
	
	public List<IRequestBodyConverter> getRequestBodyConverters() {
		return requestBodyConverters;
	}
	
	public void setRequestBodyConverters(
			List<IRequestBodyConverter> requestBodyConverters) {
		this.requestBodyConverters = requestBodyConverters;
	}
}
