package com.googlecode.jferry.invocation.commonshttpclient;

import java.io.UnsupportedEncodingException;

import javax.activation.MimeType;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.googlecode.jferry.invocation.request.DefaultRequestBodyConverterFactory;
import com.googlecode.jferry.invocation.request.IRequestBodyConverter;
import com.googlecode.jferry.invocation.request.IRequestBodyConverterFactory;
import com.googlecode.jferry.invocation.util.PathProcessor;
import com.googlecode.jferry.model.InvocationContext;
import com.googlecode.jferry.model.MethodInvocationInput;
import com.googlecode.jferry.model.RequestType;

public class PutMethodInvocationProcessor extends
		AbstractMethodInvocationProcessor {

	private IRequestBodyConverterFactory bodyConverterFactory;

	public PutMethodInvocationProcessor() {
		this.bodyConverterFactory = new DefaultRequestBodyConverterFactory();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HttpMethod createMethod(MethodInvocationInput input) {
		InvocationContext ctx = input.getContext();
		String processedPath = PathProcessor.createPath(input);

		String uriString = ctx.getBaseUrl() + ctx.getBasePath() + processedPath;
		PutMethod method = new PutMethod(uriString);

		MimeType mimeType = input.getMethodMetadata().getConsumedMimeType();

		IRequestBodyConverter requestBodyConverter = this.bodyConverterFactory
				.create(mimeType);
		String bodyString = requestBodyConverter.convert(input);

		RequestEntity requestEntity;

		try {
			requestEntity = new StringRequestEntity(bodyString, mimeType
					.getBaseType(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}

		method.setRequestEntity(requestEntity);

		return method;
	}

	@Override
	public RequestType requestType() {
		return RequestType.PUT;
	}

}
