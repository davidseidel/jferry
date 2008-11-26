package com.googlecode.jferry.invocation.commonshttpclient;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;

import com.googlecode.jferry.invocation.util.PathProcessor;
import com.googlecode.jferry.model.InvocationContext;
import com.googlecode.jferry.model.MethodInvocationInput;
import com.googlecode.jferry.model.RequestType;

public class DeleteMethodInvocationProcessor extends
		AbstractMethodInvocationProcessor {

	@Override
	protected HttpMethod createMethod(MethodInvocationInput input) {
		InvocationContext ctx = input.getContext();
		String processedPath = PathProcessor.createPath(input);
		
		String uriString = ctx.getBaseUrl() + ctx.getBasePath() + processedPath;
		DeleteMethod method = new DeleteMethod(uriString);
		return method;
	}

	@Override
	public RequestType requestType() {
		return RequestType.DELETE;
	}

}
