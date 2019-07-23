package com.jitendra.logasservice.es.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.es.model.ESAuditUiLog;

@Repository
public interface ESAuditUiLogRepository extends ElasticsearchRepository<ESAuditUiLog, Long> {

	public List<ESAuditUiLog> getBy(Long appId);
	
	public List<ESAuditUiLog> getByAppId(String appId);

	public List<ESAuditUiLog> getByLevel(Level level);

	public List<ESAuditUiLog> getByAppIdAndLevel(String appId, Level level);

	 

}
