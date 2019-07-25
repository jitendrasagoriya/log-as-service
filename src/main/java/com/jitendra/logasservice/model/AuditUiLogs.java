package com.jitendra.logasservice.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.token.SecureTokenGenerator;

@Entity
@Table(name = "AUDITLOG")
public class AuditUiLogs implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "LOGTIME")
	private Timestamp logTime;

	@Column(name = "LOGDATE")
	private Date logDate;

	@Column(name = "LOG")
	private String log;

	@Column(name = "LEVEL")
	private Level level;

	@Column(name = "APPID")
	private String appId;

	/**
	 * 
	 */
	public AuditUiLogs() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param logTime
	 * @param log
	 */
	public AuditUiLogs(Long id, Timestamp logTime, String log) {
		super();
		this.id = id;
		this.logTime = logTime;
		this.log = log;
	}

	/**
	 * @param id
	 * @param logTime
	 * @param log
	 * @param level
	 */
	public AuditUiLogs(Long id, Timestamp logTime, String log, Level level) {
		super();
		this.id = id;
		this.logTime = logTime;
		this.log = log;
		this.level = level;
	}

	public AuditUiLogs(Long id, Timestamp logTime, String log, Level level, String appId) {
		super();
		this.id = id;
		this.logTime = logTime;
		this.log = log;
		this.level = level;
		this.appId = appId;
	}

	/**
	 * @param id
	 * @param logTime
	 * @param logDate
	 * @param log
	 * @param level
	 * @param appId
	 */
	public AuditUiLogs(Long id, Timestamp logTime, Date logDate, String log, Level level, String appId) {
		super();
		this.id = id;
		this.logTime = logTime;
		this.logDate = logDate;
		this.log = log;
		this.level = level;
		this.appId = appId;
	}

	/**
	 * @return the logDate
	 */
	public Date getLogDate() {
		return logDate;
	}

	/**
	 * @param logDate the logDate to set
	 */
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the logTime
	 */
	public Timestamp getLogTime() {
		return logTime;
	}

	/**
	 * @param logTime the logTime to set
	 */
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}

	/**
	 * @return the log
	 */
	public String getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}

	/**
	 * @return the level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((log == null) ? 0 : log.hashCode());
		result = prime * result + ((logTime == null) ? 0 : logTime.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuditUiLogs other = (AuditUiLogs) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (level != other.level)
			return false;
		if (log == null) {
			if (other.log != null)
				return false;
		} else if (!log.equals(other.log))
			return false;
		if (logTime == null) {
			if (other.logTime != null)
				return false;
		} else if (!logTime.equals(other.logTime))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuditUiLogs [id=" + id + ", logTime=" + logTime + ", log=" + log + ", level=" + level + "]";
	}

	public static class AuditUiLogsBuilder {

		private AuditUiLogs auditUiLogs;

		/**
		 * 
		 */
		public AuditUiLogsBuilder() {
			auditUiLogs = new AuditUiLogs();
		}

		public AuditUiLogsBuilder(Long id, Timestamp logTime, String log) {
			auditUiLogs = new AuditUiLogs(id, logTime, log);
		}

		public AuditUiLogsBuilder(Long id, Timestamp logTime, String log, Level level) {
			auditUiLogs = new AuditUiLogs(id, logTime, log, level);
		}

		public AuditUiLogsBuilder(Long id, Timestamp logTime, String log, Level level, String appId) {
			auditUiLogs = new AuditUiLogs(id, logTime, log, level, appId);
		}

		public AuditUiLogsBuilder(Long id, Timestamp logTime, String log, Level level, String appId, Date logDate) {
			auditUiLogs = new AuditUiLogs(id, logTime, logDate, log, level, appId);
		}

		public AuditUiLogsBuilder withId(Long id) {
			auditUiLogs.setId(id);
			return this;
		}

		public AuditUiLogsBuilder withLog(String log) {
			auditUiLogs.setLog(log);
			return this;
		}

		public AuditUiLogsBuilder withAppId(String id) {
			auditUiLogs.setAppId(id);
			return this;
		}

		public AuditUiLogsBuilder withLogDate(java.sql.Date value) {
			auditUiLogs.setLogDate(value);
			return this;
		}

		public AuditUiLogs build() {
			return this.auditUiLogs;
		}

		public String buildJson() throws JsonProcessingException {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(this.auditUiLogs);
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println(new AuditUiLogsBuilder(new Long(1l), new Timestamp(System.currentTimeMillis()),
					"My first log", Level.INFO, SecureTokenGenerator.nextAppId("andolan"),new Date(System.currentTimeMillis())).buildJson());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
