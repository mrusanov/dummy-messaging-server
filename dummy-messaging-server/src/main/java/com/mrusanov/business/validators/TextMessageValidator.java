package com.mrusanov.business.validators;

import com.mrusanov.business.validators.preconditions.PayloadLengthInRangePrecondition;

public class TextMessageValidator extends MessageValidator {

	public TextMessageValidator() {
		addPrecondition(new PayloadLengthInRangePrecondition(1, 160));
	}

}
