package com.jitendra.logasservice.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.model.AuditUiLogs;
import com.jitendra.logasservice.repository.AuditUiLogsRepository;
import com.jitendra.logasservice.service.AuditUiLogsService;
import com.jitendra.logasservice.utils.DateUtility;

 

@Service
public class AuditUiLogsServiceImpl implements AuditUiLogsService<AuditUiLogsRepository> {

	@Autowired
	private AuditUiLogsRepository repository;
	
	@Override
	public AuditUiLogsRepository repository() {
		return repository;
	}

	@Override
	public List<AuditUiLogs> getAuditLog(Level level) {
		return repository.getByLevel(level);
	}

	@Override
	public List<AuditUiLogs> getAuditLog(String appId, Level level) {
		return repository.getByAppAndLevel(appId, level);
	}

	@Override
	public List<AuditUiLogs> getAuditLog(String appId, Level level, Timestamp startDate, Timestamp endDate) {
		return repository.getTodayByAppAndLevel(appId, level, startDate, endDate);
	}

	@Override
	public List<AuditUiLogs> getTodayAuditLog(String appId, Level level) {		 
		return repository.getTodayByAppAndLevel(appId, level, DateUtility.getTodayStartTime(), DateUtility.getTodayEndTime());
	}

	@Override
	public List<AuditUiLogs> getTodayAuditLog(String appId) {
		return repository.getTodayByAppAndLevel(appId, DateUtility.getTodayStartTime(), DateUtility.getTodayEndTime());
	}

	@Override
	public Integer getTodayAuditLogCount(String appId, Level level) {		 
		return repository.getTodayByAppAndLevelCount(appId, level, DateUtility.getTodayStartTime(), DateUtility.getTodayEndTime());
	}

	@Override
	public Integer getTodayAuditLogCount(String appId, Level level, Timestamp startDate) {
		return repository.getTodayByAppAndLevelCount(appId, level, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(startDate));
	}

	@Override
	public Integer getTodayAuditLogCount(String appId, Level level, Timestamp startDate, Timestamp endDate) {
		return repository.getTodayByAppAndLevelCount(appId, level, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(startDate));
	}

	@Override
	public Integer getTodayAuditLogTotalCount(String appId, Timestamp startDate) {
		int count = 0;
		count += repository.getTodayByAppAndLevelCount(appId, Level.INFO, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(startDate));
		count += repository.getTodayByAppAndLevelCount(appId, Level.DEBUG, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(startDate));
		count += repository.getTodayByAppAndLevelCount(appId, Level.ERROR, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(startDate));
		return count;
	}

	@Override
	public Integer getTodayAuditLogTotalCount(String appId, Timestamp startDate, Timestamp endDate) {
		int count = 0;
		count += repository.getTodayByAppAndLevelCount(appId, Level.INFO, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(endDate));
		count += repository.getTodayByAppAndLevelCount(appId, Level.DEBUG, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(endDate));
		count += repository.getTodayByAppAndLevelCount(appId, Level.ERROR, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(endDate));
		return count;
	}

	@Override
	public Integer getTodayAuditLogTodayTotalCount(String appId) {
		int count = 0;
		count += repository.getTodayByAppAndLevelCount(appId, Level.INFO,
				DateUtility.getTodayStartTime(new Timestamp(System.currentTimeMillis())),
				DateUtility.getTodayEndTime(new Timestamp(System.currentTimeMillis())));
		count += repository.getTodayByAppAndLevelCount(appId, Level.DEBUG,
				DateUtility.getTodayStartTime(new Timestamp(System.currentTimeMillis())),
				DateUtility.getTodayEndTime(new Timestamp(System.currentTimeMillis())));
		count += repository.getTodayByAppAndLevelCount(appId, Level.ERROR,
				DateUtility.getTodayStartTime(new Timestamp(System.currentTimeMillis())),
				DateUtility.getTodayEndTime(new Timestamp(System.currentTimeMillis())));
		return count;
	}

}
