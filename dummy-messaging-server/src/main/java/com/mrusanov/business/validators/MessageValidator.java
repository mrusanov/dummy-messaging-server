package com.mrusanov.business.validators;

import java.util.ArrayList;
import java.util.List;

import com.mrusanov.business.exceptions.PreconditionViolatedException;
import com.mrusanov.business.validators.preconditions.Precondition;
import com.mrusanov.model.Message;

public abstract class MessageValidator {

	private List<Precondition> preconditions;

	public MessageValidator() {
		preconditions = new ArrayList<>();
	}

	protected final void addPrecondition(Precondition precondition) {
		preconditions.add(precondition);
	}

	public final void validate(Message message) throws PreconditionViolatedException {
		for (Precondition precondition : preconditions) {
			precondition.check(message);
		}
	}

}
