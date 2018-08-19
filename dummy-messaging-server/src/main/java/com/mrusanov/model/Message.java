package com.mrusanov.model;

import java.util.ArrayList;
import java.util.List;

import com.mrusanov.business.exceptions.PreconditionViolatedException;
import com.mrusanov.business.validators.MessageValidator;

public abstract class Message {

	private MessageType type;

	private String payload;

	private List<MessageValidator> validators;

	public Message(String payload) {
		validators = new ArrayList<>();
		setPayload(payload);
	}

	public final void checkPreconditions() throws PreconditionViolatedException {
		for (MessageValidator validator : validators) {
			validator.validate(this);
		}
	}

	protected final void addValidator(MessageValidator validator) {
		validators.add(validator);
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getType() {
		return type.value();
	}

	public void setType(MessageType type) {
		this.type = type;
	}

}
