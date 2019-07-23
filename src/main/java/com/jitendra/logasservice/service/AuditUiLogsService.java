package com.jitendra.logasservice.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.model.AuditUiLogs;

@Service
public interface AuditUiLogsService<R> extends BaseSerivce<R> {
	public R repository();

	public List<AuditUiLogs> getAuditLog(Level level);

	public List<AuditUiLogs> getAuditLog(String appId, Level level);

	public List<AuditUiLogs> getAuditLog(String appId, Level level, Timestamp startDate, Timestamp endDate);
	
	public List<AuditUiLogs> getTodayAuditLog(String appId, Level level);	 
	
	public List<AuditUiLogs> getTodayAuditLog(String appId);
	
	public Integer getTodayAuditLogCount(String appId, Level level);
	
	public Integer getTodayAuditLogCount(String appId, Level level,Timestamp startDate);
	
	public Integer getTodayAuditLogTotalCount(String appId,Timestamp startDate);
	
	public Integer getTodayAuditLogCount(String appId, Level level,Timestamp startDate, Timestamp endDate);
	
	public Integer getTodayAuditLogTotalCount(String appId, Timestamp startDate, Timestamp endDate);
	
	public Integer getTodayAuditLogTodayTotalCount(String appId);
}
