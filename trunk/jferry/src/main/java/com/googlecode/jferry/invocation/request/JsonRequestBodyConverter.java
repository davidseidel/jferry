package com.googlecode.jferry.invocation.request;

import java.util.List;

import javax.activation.MimeType;

import net.sf.json.JSONObject;

import com.googlecode.jferry.model.MethodInvocationInput;
import com.googlecode.jferry.model.MethodMetadata;
import com.googlecode.jferry.model.MethodParameterMetadata;

public class JsonRequestBodyConverter implements IRequestBodyConverter {

	@Override
	public boolean canConvert(MimeType type) {
		if (type != null && type.getPrimaryType().equals("application")
				&& type.getSubType().equals("json")) {
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

				Object argumentToMarshal = arguments[paramCounter];
				JSONObject jsonMarshalledObject = JSONObject.fromObject(argumentToMarshal);
				result = jsonMarshalledObject.toString();
				break;
			}
		}

		return result;
	}

}
