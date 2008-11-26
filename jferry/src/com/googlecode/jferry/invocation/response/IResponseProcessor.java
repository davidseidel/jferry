package com.googlecode.jferry.invocation.response;

import javax.activation.MimeType;

public interface IResponseProcessor {
	Object process(Class<?> type, String responseString);
	
	boolean canProcess(MimeType type);
}
