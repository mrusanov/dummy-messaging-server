package com.mrusanov.model;

import com.mrusanov.model.exceptions.InvalidParameterException;

public class MessageFactory {

	public static Message createMessage(String type, String payload) throws InvalidParameterException {
		Message message = null;

		if (payload == null || payload.isEmpty()) {
			throw new InvalidParameterException("The parameter 'payload' is mandatory");
		}

		if (type.equals(MessageType.TEXT.value())) {
			message = new TextMessage(payload);
		} else if (type.equals(MessageType.EMOTION.value())) {
			message = new EmotionMessage(payload);
		} else {
			throw new InvalidParameterException("Unsupported message type: " + type);
		}

		return message;
	}

}
