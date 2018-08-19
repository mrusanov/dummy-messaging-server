package com.mrusanov.model;

import com.mrusanov.business.validators.TextMessageValidator;

public class TextMessage extends Message {

	public TextMessage(String payload) {
		super(payload);
		setType(MessageType.TEXT);
		addValidator(new TextMessageValidator());
	}

}
