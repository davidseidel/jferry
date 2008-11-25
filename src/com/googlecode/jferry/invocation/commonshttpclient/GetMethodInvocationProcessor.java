package com.googlecode.jferry.invocation.commonshttpclient;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.googlecode.jferry.invocation.util.PathProcessor;
import com.googlecode.jferry.model.InvocationContext;
import com.googlecode.jferry.model.MethodInvocationInput;
import com.googlecode.jferry.model.RequestType;

public class GetMethodInvocationProcessor extends AbstractMethodInvocationProcessor {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RequestType requestType() {
		return RequestType.GET;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HttpMethod createMethod(MethodInvocationInput input) {
		InvocationContext ctx = input.getContext();
		String processedPath = PathProcessor.createPath(input);
		
		String uriString = ctx.getBaseUrl() + ctx.getBasePath() + processedPath;
		GetMethod method = new GetMethod(uriString);
		return method;
	}

}
