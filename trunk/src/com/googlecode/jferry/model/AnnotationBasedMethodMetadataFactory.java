package com.googlecode.jferry.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class AnnotationBasedMethodMetadataFactory extends
		AbstractMethodMetadataFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RequestType lookupRequestType(Method method) {
		final RequestType[] requestTypesToTest = RequestType.values();
		final Annotation[] methodAnnotations = method.getAnnotations();

		RequestType requestTypeToReturn = null;

		for (Annotation annotation : methodAnnotations) {
			Class<? extends Annotation> annotationClass = annotation.annotationType();
			for (RequestType requestTypeToTest : requestTypesToTest) {
				if (requestTypeToTest
						.testAnnotationOfRequestType(annotationClass)) {
					requestTypeToReturn = requestTypeToTest;
					break;
				}
			}
		}

		return requestTypeToReturn;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String lookupPath(Method method) {
		String path = null;
		Path pathAnnotation = (Path) method.getAnnotation(Path.class);
		if (pathAnnotation != null) {
			path = pathAnnotation.value();
		}
		return path;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<MethodParameterMetadata> createParameterList(Method method) {
		List<MethodParameterMetadata> parameters = new ArrayList<MethodParameterMetadata>();

		int paramCount = method.getParameterTypes().length;

		if (paramCount > 0) {
			Class<?>[] paramTypes = method.getParameterTypes();
			Annotation[][] paramsAnnotations = method.getParameterAnnotations();
			for(int paramCounter = 0; paramCounter < paramCount; paramCounter++) {
				MethodParameterMetadata parameterMetadata = new MethodParameterMetadata();
				
				// extract type
				Class<?> type = paramTypes[paramCounter];
				parameterMetadata.setType(type);
				
				// extract annotations
				Annotation[] paramAnnotations = paramsAnnotations[paramCounter];
				boolean foundParameterAnnotation = false;
				for(Annotation paramAnnotation : paramAnnotations) {
					
					if(paramAnnotation.annotationType().equals(QueryParam.class)) {
						String paramName = ((QueryParam) paramAnnotation).value();
						parameterMetadata.setQueryParameterName(paramName);
						parameterMetadata.setQueryParameter(true);			
					} else if(paramAnnotation.annotationType().equals(PathParam.class)) {
						String paramName = ((PathParam) paramAnnotation).value();
						parameterMetadata.setPathParameterName(paramName);
						parameterMetadata.setPathParameter(true);
					} else {
						
					}
				}
				
				if(!foundParameterAnnotation) {
					parameterMetadata.setEntityParameter(true);
				}
				
				parameters.add(parameterMetadata);
			}
		}
		return parameters;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MimeType lookupConsumedMimeType(Method method) {
		MimeType mimeTypeToReturn = null;
		final Annotation[] methodAnnotations = method.getAnnotations();
		
		for (Annotation annotation : methodAnnotations) {
			Class<? extends Annotation> annotationClass = annotation.annotationType();
			if(annotationClass.equals(Consumes.class)) {
				// TODO: add support for multiple mime types
				String[] consumes = ((Consumes) annotation).value();
				if(consumes.length > 0) {
					String mimeTypeString = consumes[0];
					try {
						mimeTypeToReturn = new MimeType(mimeTypeString);
					} catch (MimeTypeParseException e) {
						throw new IllegalStateException(e);
					}
				}
			}
			
		}
		
		return mimeTypeToReturn;
	}
}
