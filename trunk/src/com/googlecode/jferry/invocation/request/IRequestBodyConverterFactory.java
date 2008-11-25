package com.googlecode.jferry.invocation.request;

import javax.activation.MimeType;

public interface IRequestBodyConverterFactory {
	IRequestBodyConverter create(MimeType type);
}
