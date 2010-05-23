package com.googlecode.jferry.invocation.util;

import java.util.Collection;
import java.util.List;

import com.googlecode.jferry.model.MethodInvocationInput;
import com.googlecode.jferry.model.MethodMetadata;
import com.googlecode.jferry.model.MethodParameterMetadata;

public class PathProcessor {

	public static String convertValue(Object value) {
		String convertedValue = null;
		if (!(value instanceof Collection<?>)) {
			convertedValue = String.valueOf(value);
		} else {
			// TODO: implement for collections
			throw new IllegalStateException(
					"Collections currently not supported");
		}
		return convertedValue;
	}

	public static String createPath(MethodInvocationInput input) {

		MethodMetadata methodMetadata = input.getMethodMetadata();
		if (methodMetadata == null) {
			throw new IllegalStateException("method metadata is null!");
		}

		String pathTemplate = methodMetadata.getPath();
		if (pathTemplate == null) {
			throw new IllegalStateException("path is null!");
		}
		StringBuffer processedPathTemplate = new StringBuffer(pathTemplate);
		List<MethodParameterMetadata> parameters = methodMetadata
				.getParameters();

		if (methodMetadata.getParameters() == null) {
			throw new IllegalStateException(
					"list of method parameters is null!");
		}

		if (input.getArguments() == null) {
			throw new IllegalStateException("got null arguments!");
		}
		Object[] arguments = input.getArguments();
		int paramCount = parameters.size();
		if (paramCount != arguments.length) {
			throw new IllegalStateException(
					"count of parameters is not equal to the counter of arguments!");
		}

		for (int paramCounter = 0; paramCounter < paramCount; paramCounter++) {
			MethodParameterMetadata parameter = parameters.get(paramCounter);

			if (parameter.isPathParameter()) {
				Object argument = arguments[paramCounter];

				if (parameter.getPathParameterName() == null
						|| parameter.getPathParameterName().length() == 0) {
					continue;
				}

				int index = processedPathTemplate.indexOf("{"
						+ parameter.getPathParameterName() + "}");
				if (index != -1) {
					int pathParamNameLength = parameter
							.getPathParameterName().length();

					processedPathTemplate.delete(index, index + pathParamNameLength + 2);
					
					processedPathTemplate.insert(index, argument);
				}
			}
		}
		return processedPathTemplate.toString();
	}
}
