package com.jitendra.logasservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitendra.logasservice.model.Application;
import com.jitendra.logasservice.repository.ApplicationRepository;
import com.jitendra.logasservice.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService<ApplicationRepository> {
	
	@Autowired
	private ApplicationRepository repository;

	@Override
	public ApplicationRepository repository() {
		return repository;
	}

	@Override
	public boolean isValidUser(String accessToken) {
		Application app = repository.getActive(accessToken);
		if(app!=null)
			return true;
		return false;
	}

	@Override
	public Application getUserByToken(String accessToken) {
		Application app = repository.getActive(accessToken);
		if(app!=null)
			return app;
		return null;
	}

}
