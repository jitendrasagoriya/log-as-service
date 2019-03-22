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
import com.jitendra.logasservice.model.AuditUiLogs;
import com.jitendra.logasservice.service.impl.ApplicationServiceImpl;
import com.jitendra.logasservice.service.impl.AuditUiLogsServiceImpl;

 
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/ui/log/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AuditUiLogEndpoint {
	
	private final Logger logger = LoggerFactory.getLogger(AuditUiLogEndpoint.class);

	@Autowired
	private AuditUiLogsServiceImpl service;
	
	 @Autowired 
	  private ApplicationServiceImpl applicationService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addEvent(@RequestBody AuditUiLogs logs,@RequestHeader("X-AUTH-LOG-HEADER") String token) {
		
		try {
			Application application = applicationService.getUserByToken(token);
			logs.setAppId(application.getId());
			return new ResponseEntity<AuditUiLogs>(service.repository().save(logs), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<AuditUiLogs>> getAll() {
		return new ResponseEntity<List<AuditUiLogs>>(service.repository().findAll(), HttpStatus.OK);
	}

}
