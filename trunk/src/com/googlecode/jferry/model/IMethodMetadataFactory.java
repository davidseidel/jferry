package com.googlecode.jferry.model;

import java.lang.reflect.Method;

public interface IMethodMetadataFactory {
	MethodMetadata create(Method method);
}
