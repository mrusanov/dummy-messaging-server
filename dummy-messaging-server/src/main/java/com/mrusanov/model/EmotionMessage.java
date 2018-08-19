package com.mrusanov.model;

import com.mrusanov.business.validators.EmotionMessageValidator;

public class EmotionMessage extends Message {

	public EmotionMessage(String payload) {
		super(payload);
		addValidator(new EmotionMessageValidator());
	}

	@Override
	public MessageType getType() {
		return MessageType.EMOTION;
	}

}
