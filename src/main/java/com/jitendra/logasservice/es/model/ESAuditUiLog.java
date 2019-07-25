package com.jitendra.logasservice.es.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.token.SecureTokenGenerator;


@Document(indexName = "log", type = "logs")
public class ESAuditUiLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private Timestamp logTime;
	
	private String log;
	
	private Level level;
	
	private String appId;
	
	private java.sql.Date logDate;

	/**
	 * 
	 */
	public ESAuditUiLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param logTime
	 * @param log
	 */
	public ESAuditUiLog(Long id, Timestamp logTime, String log) {
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
	public ESAuditUiLog(Long id, Timestamp logTime, String log, Level level) {
		super();
		this.id = id;
		this.logTime = logTime;
		this.log = log;
		this.level = level;
	}
	
	public ESAuditUiLog(Long id, Timestamp logTime, String log, Level level, String appId) {
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
	 * @param log
	 * @param level
	 * @param appId
	 * @param logDate
	 */
	public ESAuditUiLog(Long id, Timestamp logTime, String log, Level level, String appId, java.sql.Date logDate) {
		super();
		this.id = id;
		this.logTime = logTime;
		this.log = log;
		this.level = level;
		this.appId = appId;
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
	
	

	/**
	 * @return the logDate
	 */
	public java.sql.Date getLogDate() {
		return logDate;
	}

	/**
	 * @param logDate the logDate to set
	 */
	public void setLogDate(java.sql.Date logDate) {
		this.logDate = logDate;
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
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
		ESAuditUiLog other = (ESAuditUiLog) obj;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ESAuditUiLog [id=" + id + ", logTime=" + logTime + ", log=" + log + ", level=" + level + ", appId="
				+ appId + "]";
	}
	
	public static class ESAuditUiLogsBuilder{
		
		
		private ESAuditUiLog auditUiLogs;

		/**
		 * 
		 */
		public ESAuditUiLogsBuilder() {
			auditUiLogs = new ESAuditUiLog();
		}
		
		public ESAuditUiLogsBuilder(Long id, Timestamp logTime, String log) {
			auditUiLogs = new ESAuditUiLog(id,logTime,log);
		}
		
		public ESAuditUiLogsBuilder(Long id, Timestamp logTime, String log, Level level) {
			auditUiLogs = new ESAuditUiLog(id,logTime,log,level);
		}
		
		public ESAuditUiLogsBuilder(Long id, Timestamp logTime, String log, Level level,String appId) {
			auditUiLogs = new ESAuditUiLog(id,logTime,log,level,appId);
		}
		
		public ESAuditUiLogsBuilder(Long id, Timestamp logTime, String log, Level level,String appId,java.sql.Date logDate) {
			auditUiLogs = new ESAuditUiLog(id,logTime,log,level,appId,logDate);
		}
		
		public ESAuditUiLogsBuilder withId(Long id) {
			auditUiLogs.setId(id);
			return this;
		} 
		
		public ESAuditUiLogsBuilder withLog(String log) {
			auditUiLogs.setLog(log);
			return this;
		}
		
		public ESAuditUiLogsBuilder withAppId(String id) {
			auditUiLogs.setAppId(id);
			return this;
		}
		
		public ESAuditUiLogsBuilder withLogDate(java.sql.Date value) {
			auditUiLogs.setLogDate(value);;
			return this;
		}
		
		public ESAuditUiLog build(){
			return this.auditUiLogs;
		}
		
		public String buildJson() throws JsonProcessingException {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(this.auditUiLogs);
		}
		
		public ESAuditUiLog getObject(String jsonString) throws JsonProcessingException {
			final Gson builder = new GsonBuilder()
				    .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

						@Override
						public Date deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
								JsonDeserializationContext context) throws JsonParseException {
							return new Date(json.getAsJsonPrimitive().getAsLong()); 
						} 
				       
				    })
				    .create();
			return builder.fromJson(jsonString, ESAuditUiLog.class);
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out
					.println(new ESAuditUiLogsBuilder(new Long(1l), new Timestamp(System.currentTimeMillis()), "My first log",Level.INFO,SecureTokenGenerator.nextAppId("andolan"),new java.sql.Date(System.currentTimeMillis()))
							.buildJson());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
