package com.jitendra.logasservice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jitendra.logasservice.service.impl.ApplicationServiceImpl;

@Component
public class AuthenticatorInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticatorInterceptor.class);

	
	  @Autowired 
	  private ApplicationServiceImpl service;
	 

	/**
	 * This is not a good practice to use sysout. Always integrate any logger with
	 * your application. We will discuss about integrating logger with spring boot
	 * application in some later article
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("In preHandle we are Intercepting the Request");
		if (logger.isDebugEnabled())
			logger.debug("---------------------------------------------");
		String requestURI = request.getRequestURI();
		Integer personId = ServletRequestUtils.getIntParameter(request, "personId", 0);

		/*****************************
		 * URL NOT CHECK AUTHENTICATION
		 *************************************/		
		
		if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
			return true;
		}
 		if ( request.getRequestURI().contains("api/admin/")) {
			return true;
		}
		if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().contains("api/authentication/")) {
			return true;
		}
		if (request.getMethod().equalsIgnoreCase("GET") && request.getRequestURI().contains("api/authentication/")) {
			return true;
		}
		
		if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().contains("api/authentication/token")) {
			return true;
		}

		 
		/*************************************
		 * CHECK HEADER
		 *************************************/
		
		String accessToken = request.getHeader("X-AUTH-LOG-HEADER");
		if (accessToken == null || accessToken.isEmpty()) {
			response.getWriter().write("Authentication failed.");
			response.setStatus(401);
			return false;
		}
		  
		/**************************************
		 * CHECK AUTHENTICTION
		 *************************************/
		
		Boolean isValid  = service.isValidUser(accessToken);

		if (logger.isDebugEnabled())
			logger.debug("isValid :" + (isValid == null));
		if (!isValid) {
			response.getWriter().write("Authentication failed.");
			response.setStatus(401);
			return false;
		}
		/*****************************
		 * CHECK SESSION TIME OUT
		 *************************************/
		
		/*Timestamp lastLogin = authentication.getLastLogin();
		Long lastLoginInMills = lastLogin.getTime();
		if (System.currentTimeMillis() > (lastLoginInMills + authentication.getTokenTimeout())) {
			response.getWriter().write("Session expire. Please login again");
			response.setStatus(401);
			return false;
		}*/
		 

		// if(logger.isDebugEnabled()) logger.debug(accessToken);
		if (logger.isDebugEnabled())
			logger.debug("RequestURI::" + requestURI + " || Search for Person with personId ::" + personId);
		if (logger.isDebugEnabled())
			logger.debug("---------------------------------------------");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("---------------------------------------------");
		if (logger.isDebugEnabled())
			logger.debug("In postHandle request processing " + "completed by @RestController");
		if (logger.isDebugEnabled())
			logger.debug("---------------------------------------------");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("---------------------------------------------");
		if (logger.isDebugEnabled())
			logger.debug("In afterCompletion Request Completed");
		if (logger.isDebugEnabled())
			logger.debug("---------------------------------------------");
	}

}
