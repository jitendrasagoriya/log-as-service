package com.jitendra.logasservice.endpoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.logasservice.model.Application;
import com.jitendra.logasservice.mongodb.model.MDBAuditLog;
import com.jitendra.logasservice.mongodb.service.MDBAuditLogService;
import com.jitendra.logasservice.service.impl.ApplicationServiceImpl;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/mdb/ui/log/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class MDBAuditLogEndpoint {

	
	private final Logger logger = LoggerFactory.getLogger(AuditUiLogEndpoint.class);

	@Autowired
	private MDBAuditLogService service;
	
	 @Autowired 
	  private ApplicationServiceImpl applicationService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addLog(@RequestBody MDBAuditLog logs,@RequestHeader("X-AUTH-LOG-HEADER") String token) {
		
		try {
			Application application = applicationService.getUserByToken(token);
			logs.setAppId(application.getId());
			return new ResponseEntity<MDBAuditLog>(service.repository().save(logs), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<MDBAuditLog>> getAll(@RequestHeader("X-AUTH-LOG-HEADER") String token) {
		Application application = applicationService.getUserByToken(token);		
		return new ResponseEntity<List<MDBAuditLog>>(service.findByAppId(application.getId()), HttpStatus.OK);
	}
	
}
