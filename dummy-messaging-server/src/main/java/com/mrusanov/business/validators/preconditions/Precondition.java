package com.mrusanov.business.validators.preconditions;

import com.mrusanov.business.exceptions.PreconditionViolatedException;
import com.mrusanov.model.Message;

public interface Precondition {

	void check(Message message) throws PreconditionViolatedException;

}
