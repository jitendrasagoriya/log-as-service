package com.jitendra.logasservice.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jitendra.logasservice.token.SecureTokenGenerator;


@Entity
@Table(name = "APPLICATION")
public class Application implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "APPNAME")
	private String appName;
	
	@Column(name = "ACCESSTOKEN")
	private String accessToken;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "ACCESS")
	private String access;
	
	@Column(name = "CREATIONTIME")
	private Timestamp onBoardTime;
	
	@Column(name= "EMAIL")
	private String email;
	
	@Column(name= "PASSWORD")
	private String password;

	/**
	 * 
	 */
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param appName
	 * @param accessToken
	 * @param description
	 * @param access
	 * @param onBoardTime
	 */
	public Application(String id, String appName, String accessToken, String description, String access,
			Timestamp onBoardTime) {
		super();
		this.id = id;
		this.appName = appName;
		this.accessToken = accessToken;
		this.description = description;
		this.access = access;
		this.onBoardTime = onBoardTime;
	}
	
	

	/**
	 * @param id
	 * @param appName
	 * @param accessToken
	 * @param description
	 * @param access
	 * @param onBoardTime
	 * @param email
	 * @param password
	 */
	public Application(String id, String appName, String accessToken, String description, String access,
			Timestamp onBoardTime, String email, String password) {
		super();
		this.id = id;
		this.appName = appName;
		this.accessToken = accessToken;
		this.description = description;
		this.access = access;
		this.onBoardTime = onBoardTime;
		this.email = email;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the access
	 */
	public String getAccess() {
		return access;
	}

	/**
	 * @param access the access to set
	 */
	public void setAccess(String access) {
		this.access = access;
	}

	/**
	 * @return the onBoardTime
	 */
	public Timestamp getOnBoardTime() {
		return onBoardTime;
	}

	/**
	 * @param onBoardTime the onBoardTime to set
	 */
	public void setOnBoardTime(Timestamp onBoardTime) {
		this.onBoardTime = onBoardTime;
	}
	
	
	

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access == null) ? 0 : access.hashCode());
		result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((onBoardTime == null) ? 0 : onBoardTime.hashCode());
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
		Application other = (Application) obj;
		if (access == null) {
			if (other.access != null)
				return false;
		} else if (!access.equals(other.access))
			return false;
		if (accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!accessToken.equals(other.accessToken))
			return false;
		if (appName == null) {
			if (other.appName != null)
				return false;
		} else if (!appName.equals(other.appName))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (onBoardTime == null) {
			if (other.onBoardTime != null)
				return false;
		} else if (!onBoardTime.equals(other.onBoardTime))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Application [id=" + id + ", appName=" + appName + ", accessToken=" + accessToken + ", description="
				+ description + ", access=" + access + ", onBoardTime=" + onBoardTime + "]";
	}
	
	public static class ApplicationBuilder {
		
		private Application application;

		/**
		 * 
		 */
		public ApplicationBuilder() {
			application = new Application();
		}
		
		public ApplicationBuilder(String id, String appName, String accessToken, String description, String access,String email,String password) {
			application = new Application(id,appName,accessToken,description,access, new Timestamp(System.currentTimeMillis()),email,password);
		}
				
		public ApplicationBuilder(String id, String appName, String accessToken, String description, String access) {
			application = new Application(id,appName,accessToken,description,access, new Timestamp(System.currentTimeMillis()));
		}
		
		public ApplicationBuilder withOnboardTime() {
			application.setOnBoardTime(new Timestamp(System.currentTimeMillis()));
			return this;
		}
		
		public ApplicationBuilder withDescription(String value) {
			application.setDescription(value);
			return this;
		}
		
		public ApplicationBuilder withAppName(String appName) {
			application.setAppName(appName);
			return this;
		}
		
		public ApplicationBuilder withAccessToken(String accessToken) {
			application.setAccessToken(accessToken);
			return this;
		}
		
		public ApplicationBuilder withId(String id) {
			application.setId(id);
			return this;
		}
		public ApplicationBuilder withAccess(String access) {
			application.setAccess(access);
			return this;
		}
		
		public ApplicationBuilder withEmail(String access) {
			application.setEmail(access);
			return this;
		}
		
		public ApplicationBuilder withPassword(String access) {
			application.setPassword(access);
			return this;
		}
		
		public Application build(){
			return this.application;
		}
		
		public String buildJson() throws JsonProcessingException {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(this.application);
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out
					.println( new ApplicationBuilder(SecureTokenGenerator.nextAppId("ANDOLA-UI"), "ANDOLA-UI",SecureTokenGenerator.getToken() ,
							"ANDOLA-UI", "ADMIN","jitendrasagoriya@yahoo.co.in","123456789").buildJson() );
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
