package com.mrusanov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrusanov.persistence.entities.RequestEntity;
import com.mrusanov.persistence.repositories.RequestRepository;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestRepository repository;

	@Override
	public void logRequestWithParameters(String type, String payload) {
		RequestEntity request = new RequestEntity(type, payload);
		repository.saveAndFlush(request);
	}

}
