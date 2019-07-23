package com.jitendra.logasservice.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.mongodb.model.MDBAuditLog;

@Repository
public interface MDBAuditLogRepository  extends MongoRepository<MDBAuditLog, Long> {

	
	List<MDBAuditLog> findByAppId(String appId);
	
	List<MDBAuditLog> findByAppIdAndLevel(String appId,Level level);
}
