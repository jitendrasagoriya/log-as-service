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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.es.model.ESAuditUiLog;
import com.jitendra.logasservice.es.service.ESAuditUiLogService;
import com.jitendra.logasservice.model.Application;
import com.jitendra.logasservice.service.impl.ApplicationServiceImpl;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/es/ui/log/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ESAuditLogEndpoint {

	
	private final Logger logger = LoggerFactory.getLogger(AuditUiLogEndpoint.class);

	@Autowired
	private ESAuditUiLogService service;
	
	 @Autowired 
	  private ApplicationServiceImpl applicationService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addEvent(@RequestBody ESAuditUiLog logs,@RequestHeader("X-AUTH-LOG-HEADER") String token) {
		
		try {
			Application application = applicationService.getUserByToken(token);
			logs.setAppId(application.getId());
			return new ResponseEntity<ESAuditUiLog>(service.save(logs), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<ESAuditUiLog>> getAll(@RequestHeader("X-AUTH-LOG-HEADER") String token) {
		Application application = applicationService.getUserByToken(token);
		
		return new ResponseEntity<List<ESAuditUiLog>>(service.getByAppId(application.getId()), HttpStatus.OK);
	}
	
	@GetMapping(path ="today")
	public ResponseEntity<?> getByAppId(@RequestHeader("X-AUTH-LOG-HEADER") String token ) {
		Application application = applicationService.getUserByToken(token);
		try {
			return new ResponseEntity<List<ESAuditUiLog>>(service.getTodayByApp(application.getId()), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<Exception>(e, HttpStatus.OK);
		}
	}
	
	@GetMapping(path ="today/level/{level}")
	public ResponseEntity<?> getTodayByLevel(@RequestHeader("X-AUTH-LOG-HEADER") String token,@PathVariable("level") Level level ) {
		Application application = applicationService.getUserByToken(token);
		try {
			return new ResponseEntity<List<ESAuditUiLog>>(service.getTodayByAppAndLevel(application.getId(),level), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(),e);
			return new ResponseEntity<Exception>(e, HttpStatus.OK);
		}
	}
	
	@GetMapping(path ="level/{level}")
	public ResponseEntity<List<ESAuditUiLog>> getByAppId(@RequestHeader("X-AUTH-LOG-HEADER") String token,@PathVariable("level") Level level) {
		Application application = applicationService.getUserByToken(token);
		return new ResponseEntity<List<ESAuditUiLog>>(service.getByAppAndLevel(application.getId(),level), HttpStatus.OK);
	}

}
