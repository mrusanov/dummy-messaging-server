package com.mrusanov.model;

public enum MessageType {

	TEXT("send_text"), 
	EMOTION("send_emotion");

	private final String value;

	MessageType(final String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

}
