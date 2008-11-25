package com.googlecode.jferry.invocation.commonshttpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;

import com.googlecode.jferry.invocation.IMethodInvocationProcessor;
import com.googlecode.jferry.invocation.MethodInvocationException;
import com.googlecode.jferry.invocation.response.DefaultResponseProcessorFactory;
import com.googlecode.jferry.invocation.response.IResponseProcessor;
import com.googlecode.jferry.invocation.response.IResponseProcessorFactory;
import com.googlecode.jferry.model.MethodInvocationInput;
import com.googlecode.jferry.model.MethodMetadata;

// TODO: add capabilities to use a http proxy
// TODO: use generic Method Invocation with adapters to concrete technology
public abstract class AbstractMethodInvocationProcessor implements
		IMethodInvocationProcessor {
	
	
	private IResponseProcessorFactory responseProcessorFactory;
	
	public AbstractMethodInvocationProcessor() {
		responseProcessorFactory = new DefaultResponseProcessorFactory();
	}

	/**
	 * Creates the HTTP method to perform the request.
	 * 
	 * @param input invocation input
	 * @return HTTP method to perform the request
	 */
	protected abstract HttpMethod createMethod(MethodInvocationInput input);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object invoke(MethodInvocationInput input)
			throws MethodInvocationException {
		Object returnValue = null;
		HttpMethod method = this.createMethod(input);
		MethodMetadata methodMetadata = input.getMethodMetadata();
		
		HttpClient client = new HttpClient();
		try {
			int statusCode = client.executeMethod(method);
			
			// TODO: add handling for other status codes
			if(statusCode == HttpStatus.SC_OK) {
				InputStream responseStream = method.getResponseBodyAsStream();
				
				// converts response stream into string
				BufferedReader bufferedResponseStream = new BufferedReader(
						new InputStreamReader(responseStream));
				StringBuffer sb = new StringBuffer();
				String temp = bufferedResponseStream.readLine();
				while (temp != null) {
					sb.append(temp);
					sb.append("\n");
					temp = bufferedResponseStream.readLine();
				}
				String responseString = sb.toString();

				// process response
				// TODO: add support for multiple content types
				Header contentTypeHeader = method.getResponseHeader("Content-type");
				String contentType = contentTypeHeader.getValue();
				MimeType mimeType = new MimeType(contentType);
				IResponseProcessor processor = this.responseProcessorFactory.create(mimeType);
				returnValue = processor.process(methodMetadata.getReturnType(), responseString);
			}
		} catch (HttpException e) {
			throw new MethodInvocationException(e);
		} catch (IOException e) {
			throw new MethodInvocationException(e);
		} catch (MimeTypeParseException e) {
			throw new MethodInvocationException(e);
		}
		return returnValue;
	}
	
	public IResponseProcessorFactory getResponseProcessorFactory() {
		return responseProcessorFactory;
	}

	public void setResponseProcessorFactory(
			IResponseProcessorFactory responseProcessorFactory) {
		this.responseProcessorFactory = responseProcessorFactory;
	}

}
