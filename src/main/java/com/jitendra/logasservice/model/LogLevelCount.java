package com.jitendra.logasservice.model;

import java.sql.Date;

import com.jitendra.logasservice.enums.Level;

public class LogLevelCount {
	
	private Level level;
	
	private Date logDate;
	
	private int count;

	
	
	/**
	 * 
	 */
	public LogLevelCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param level
	 * @param logDate
	 * @param count
	 */
	public LogLevelCount(Level level, Date logDate, Integer count) {
		super();
		this.level = level;
		this.logDate = logDate;
		this.count = count;
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
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((logDate == null) ? 0 : logDate.hashCode());
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
		LogLevelCount other = (LogLevelCount) obj;
		if (count != other.count)
			return false;
		if (level != other.level)
			return false;
		if (logDate == null) {
			if (other.logDate != null)
				return false;
		} else if (!logDate.equals(other.logDate))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LogLevelCount [level=" + level + ", logDate=" + logDate + ", count=" + count + "]";
	}
	
	

}
