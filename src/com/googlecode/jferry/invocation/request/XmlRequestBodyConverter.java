package com.googlecode.jferry.invocation.request;

import java.io.StringWriter;
import java.util.List;

import javax.activation.MimeType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.googlecode.jferry.model.MethodInvocationInput;
import com.googlecode.jferry.model.MethodMetadata;
import com.googlecode.jferry.model.MethodParameterMetadata;

public class XmlRequestBodyConverter implements IRequestBodyConverter {

	@Override
	public boolean canConvert(MimeType type) {
		if (type != null && type.getPrimaryType().equals("text")
				&& type.getSubType().equals("xml")) {
			return true;
		}
		return false;
	}

	@Override
	public String convert(MethodInvocationInput input) {
		String result = null;

		MethodMetadata methodMetadata = input.getMethodMetadata();

		List<MethodParameterMetadata> parameters = methodMetadata
				.getParameters();
		Object arguments[] = input.getArguments();

		for (int paramCounter = 0; paramCounter < parameters.size(); paramCounter++) {
			MethodParameterMetadata parameterMetadata = parameters
					.get(paramCounter);
			if (parameterMetadata.isEntityParameter()) {
				Class<?> type = parameterMetadata.getType();
				try {
					JAXBContext ctx = JAXBContext.newInstance(type);
					Marshaller marshaller = ctx.createMarshaller();
					StringWriter writer = new StringWriter();
					Object argumentToMarshal = arguments[paramCounter];
					marshaller.marshal(argumentToMarshal, writer);
					result = writer.toString();
				} catch (JAXBException e) {
					throw new IllegalStateException(e);
				}
			}
		}

		return result;
	}

}
