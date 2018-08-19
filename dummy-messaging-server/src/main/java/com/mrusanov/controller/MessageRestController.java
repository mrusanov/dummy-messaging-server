package com.mrusanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mrusanov.business.exceptions.PreconditionViolatedException;
import com.mrusanov.model.Message;
import com.mrusanov.model.MessageFactory;
import com.mrusanov.model.exceptions.InvalidParameterException;
import com.mrusanov.services.RequestService;

@RestController
public class MessageRestController {

	@Autowired
	private RequestService requestService;

	@RequestMapping(value = "/messages/{type}", method = RequestMethod.POST)
	public ResponseEntity<String> sendMessage(@PathVariable(value = "type") String type,
			@ModelAttribute(name = "payload") String payload) {

		HttpStatus status;

		try {
			Message message = MessageFactory.createMessage(type, payload);
			message.checkPreconditions();
			status = HttpStatus.CREATED;
		} catch (InvalidParameterException e) {
			// When type is not supported or payload parameter is missing.
			status = HttpStatus.BAD_REQUEST;
		} catch (PreconditionViolatedException e) {
			// When preconditions are not met.
			status = HttpStatus.PRECONDITION_FAILED;
		}

		// Every request to the endpoint is stored in the database.
		requestService.logRequestWithParameters(type, payload);

		return ResponseEntity.status(status).body(null);
	}

}
