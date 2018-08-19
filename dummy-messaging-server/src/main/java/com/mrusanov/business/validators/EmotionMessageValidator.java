package com.mrusanov.business.validators;

import com.mrusanov.business.validators.preconditions.PayloadLengthInRangePrecondition;
import com.mrusanov.business.validators.preconditions.PayloadWithoutDigitsPrecondition;

public class EmotionMessageValidator extends MessageValidator {

	public EmotionMessageValidator() {
		addPrecondition(new PayloadLengthInRangePrecondition(2, 10));
		addPrecondition(new PayloadWithoutDigitsPrecondition());
	}

}
