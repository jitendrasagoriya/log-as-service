package com.jitendra.logasservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitendra.logasservice.repository.AuditUiLogsRepository;
import com.jitendra.logasservice.service.AuditUiLogsService;

 

@Service
public class AuditUiLogsServiceImpl implements AuditUiLogsService<AuditUiLogsRepository> {

	@Autowired
	private AuditUiLogsRepository repository;
	
	@Override
	public AuditUiLogsRepository repository() {
		return repository;
	}

}
