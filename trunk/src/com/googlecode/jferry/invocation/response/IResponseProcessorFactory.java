package com.googlecode.jferry.invocation.response;

import javax.activation.MimeType;

public interface IResponseProcessorFactory {
	IResponseProcessor create(MimeType type);
}
