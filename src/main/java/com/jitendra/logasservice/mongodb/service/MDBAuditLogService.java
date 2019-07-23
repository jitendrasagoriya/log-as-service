package com.jitendra.logasservice.mongodb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.mongodb.model.MDBAuditLog;
import com.jitendra.logasservice.mongodb.repository.MDBAuditLogRepository;

@Service
public interface MDBAuditLogService {

	public MDBAuditLogRepository repository();

	List<MDBAuditLog> findByAppId(String appId);

	List<MDBAuditLog> findByAppIdAndLevel(String appId, Level level);

}
