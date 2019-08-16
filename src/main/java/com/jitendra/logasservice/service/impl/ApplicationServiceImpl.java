package com.jitendra.logasservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitendra.logasservice.model.Application;
import com.jitendra.logasservice.repository.ApplicationRepository;
import com.jitendra.logasservice.service.ApplicationService;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService<ApplicationRepository> {

	private final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

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

	@Override
	public List<Application> findAll() {
		if (logger.isDebugEnabled())
			logger.debug("inside find all");

		List<Application> applications = null;
		try {
			applications = repository.findAll();
			applications.forEach(application -> application.setPassword(""));
		}catch (Exception e){
			logger.error(e.getLocalizedMessage(),e);
		}
		return applications;
	}



}
