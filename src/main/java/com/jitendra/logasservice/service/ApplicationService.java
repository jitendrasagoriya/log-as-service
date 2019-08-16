package com.jitendra.logasservice.service;

import org.springframework.stereotype.Service;

import com.jitendra.logasservice.model.Application;

import java.util.List;

@Service
public interface ApplicationService<R> extends BaseSerivce<R> {
	
	public R repository();

	public boolean isValidUser(String accessToken);
	
	public Application getUserByToken(String accessToken);

	public List<Application> findAll();
}
