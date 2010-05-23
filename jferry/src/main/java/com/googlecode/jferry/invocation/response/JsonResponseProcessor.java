package com.googlecode.jferry.invocation.response;

import javax.activation.MimeType;

import net.sf.json.JSONObject;

/**
 * Processor to convert JSON serialized objects to Java Beans
 * 
 * @author David Seidel &lt;seidel.david@googlemail.com&gt;
 *
 */
public class JsonResponseProcessor implements IResponseProcessor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canProcess(MimeType type) {
		if (type != null && type.getPrimaryType().equals("application")
				&& type.getSubType().equals("json")) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object process(Class<?> type, String responseString) {
		JSONObject jsonMarshalledObject = JSONObject.fromObject(responseString);
		Object returnValue = JSONObject.toBean(jsonMarshalledObject, type);
		return returnValue;
	}

}
