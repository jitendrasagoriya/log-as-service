package com.jitendra.logasservice.es.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.es.model.ESAuditUiLog;

@Service
public interface ESAuditUiLogService {

	ESAuditUiLog save(ESAuditUiLog auditUiLog);

	void delete(ESAuditUiLog book);

	ESAuditUiLog findOne(Long id);

	Iterable<ESAuditUiLog> findAll();

	List<ESAuditUiLog> getByAppId(String appId);

	List<ESAuditUiLog> getByAppId(Long appId, PageRequest pageRequest);

	List<ESAuditUiLog> getByAppAndLevel(String appId, Level level);

	List<ESAuditUiLog> getByAppAndLevel(String appId, Level level, Timestamp startDate, Timestamp endDate) throws JsonProcessingException;

	List<ESAuditUiLog> getByAppAndLevel(String appId, Timestamp startDate, Timestamp endDate) throws JsonProcessingException;

	List<ESAuditUiLog> getTodayByAppAndLevel(String appId, Level level) throws JsonProcessingException;

	List<ESAuditUiLog> getTodayByApp(String appId) throws JsonProcessingException;

}
