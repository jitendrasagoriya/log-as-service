package com.jitendra.logasservice.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.model.AuditUiLogs;

@Repository
public interface AuditUiLogsRepository extends JpaRepository<AuditUiLogs, Long> {

	@Query("SELECT A FROM AuditUiLogs A WHERE A.level = :level")
	public List<AuditUiLogs> getByLevel(@Param("level") Level level);
	
	@Query("SELECT A FROM AuditUiLogs A WHERE A.appId = :appId")
	public List<AuditUiLogs> getByAppId(@Param("appId") String appId);

	@Query("SELECT A FROM AuditUiLogs A WHERE A.appId = :appId AND A.level = :level")
	public List<AuditUiLogs> getByAppAndLevel(@Param("appId") String appId, @Param("level") Level level);

	@Query("SELECT A FROM AuditUiLogs A WHERE A.appId = :appId AND A.level = :level AND A.logTime between :startDate AND :endDate")
	public List<AuditUiLogs> getTodayByAppAndLevel(@Param("appId") String appId, @Param("level") Level level,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
	
	@Query("SELECT COUNT(A) FROM AuditUiLogs A WHERE A.appId = :appId AND A.level = :level AND A.logTime between :startDate AND :endDate")
	public Integer getTodayByAppAndLevelCount(@Param("appId") String appId, @Param("level") Level level,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
	
	@Query("SELECT A FROM AuditUiLogs A WHERE A.appId = :appId AND A.logTime between :startDate AND :endDate")
	public List<AuditUiLogs> getTodayByAppAndLevel(@Param("appId") String appId,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
	
	@Query("SELECT A FROM AuditUiLogs A WHERE A.appId = :appId AND A.logTime between :startDate AND :endDate")
	public List<AuditUiLogs> getByAppId(@Param("appId") String appId,
			@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

}
