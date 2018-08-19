package com.mrusanov.business.validators.preconditions;

import com.mrusanov.business.exceptions.PreconditionViolatedException;
import com.mrusanov.model.Message;

public class PayloadLengthInRangePrecondition implements Precondition {

	private int min;

	private int max;

	public PayloadLengthInRangePrecondition(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public void check(Message message) throws PreconditionViolatedException {
		String payload = message.getPayload();
		if ((min > payload.length()) || (payload.length() > max)) {
			throw new PreconditionViolatedException("For message with type " + message.getType()
					+ " the payload length should be between " + min + " and " + max);
		}
	}

}
