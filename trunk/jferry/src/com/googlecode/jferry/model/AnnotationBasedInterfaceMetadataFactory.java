package com.googlecode.jferry.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

public class AnnotationBasedInterfaceMetadataFactory extends AbstractInterfaceMetadataFactory  {
	
	protected String lookupBasePath(Class<?> interfaze) {
		Annotation[] annotations = interfaze.getAnnotations();
		String basePath = null;
		
		for(Annotation annotation : annotations) {
			if(annotation instanceof Path) {
				Path pathAnnotation = (Path) annotation;
				basePath = pathAnnotation.value();
			}
		}
		
		return basePath;
	}

	@Override
	protected List<MethodMetadata> lookupMethods(Class<?> interfaze) {
		Method[] methods = interfaze.getMethods();
		List<MethodMetadata> methodMetadata = new ArrayList<MethodMetadata>();
		
		for(Method method : methods) {
			MethodMetadata metaData = new AnnotationBasedMethodMetadataFactory().create(method);
			methodMetadata.add(metaData);
		}
		
		return methodMetadata;
		
	}
	
	

}
