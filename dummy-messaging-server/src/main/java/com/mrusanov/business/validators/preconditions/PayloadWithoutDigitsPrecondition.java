package com.mrusanov.business.validators.preconditions;

import com.mrusanov.business.exceptions.PreconditionViolatedException;
import com.mrusanov.model.Message;

public class PayloadWithoutDigitsPrecondition implements Precondition {

	private static final String CONTAINS_DIGITS_PATTERN = ".*[0-9].*";

	@Override
	public void check(Message message) throws PreconditionViolatedException {
		String payload = message.getPayload();
		if (payload.matches(CONTAINS_DIGITS_PATTERN)) {
			throw new PreconditionViolatedException(
					"A message with type " + message.getType() + " cannot contain characters between 0 and 9");
		}
	}

}
