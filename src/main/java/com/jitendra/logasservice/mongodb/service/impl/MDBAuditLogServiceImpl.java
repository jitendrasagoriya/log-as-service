package com.jitendra.logasservice.mongodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.mongodb.model.MDBAuditLog;
import com.jitendra.logasservice.mongodb.repository.MDBAuditLogRepository;
import com.jitendra.logasservice.mongodb.service.MDBAuditLogService;

@Repository
public class MDBAuditLogServiceImpl implements MDBAuditLogService {

	@Autowired
	MDBAuditLogRepository auditLogRepository;

	@Override
	public MDBAuditLogRepository repository() {
		return auditLogRepository;
	}

	@Override
	public List<MDBAuditLog> findByAppId(String appId) {
		return auditLogRepository.findByAppId(appId);
	}

	@Override
	public List<MDBAuditLog> findByAppIdAndLevel(String appId, Level level) {
		return auditLogRepository.findByAppIdAndLevel(appId, level);
	}
	
	
}
