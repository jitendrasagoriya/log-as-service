package com.jitendra.logasservice.endpoint;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.model.Application;
import com.jitendra.logasservice.model.AuditUiLogs;
import com.jitendra.logasservice.model.LogLevelCount;
import com.jitendra.logasservice.service.impl.ApplicationServiceImpl;
import com.jitendra.logasservice.service.impl.AuditUiLogsServiceImpl;
import com.jitendra.logasservice.utils.DateUtility;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/db/ui/log/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AuditUiLogEndpoint {

	private final Logger logger = LoggerFactory.getLogger(AuditUiLogEndpoint.class);

	@Autowired
	private AuditUiLogsServiceImpl service;

	@Autowired
	private ApplicationServiceImpl applicationService;

	@PostMapping(path = "logbydate", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addEvent(@RequestBody AuditUiLogs logs, @RequestHeader("X-AUTH-LOG-HEADER") String token) {

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

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addToDayLog(@RequestBody AuditUiLogs logs,
			@RequestHeader("X-AUTH-LOG-HEADER") String token) {

		try {
			Application application = applicationService.getUserByToken(token);
			logs.setAppId(application.getId());
			if (logs.getLogTime() == null)
				logs.setLogTime(new Timestamp(System.currentTimeMillis()));
			if (logs.getLogDate() == null)
				logs.setLogDate(new java.sql.Date(System.currentTimeMillis()));

			return new ResponseEntity<AuditUiLogs>(service.repository().save(logs), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping()
	public ResponseEntity<List<AuditUiLogs>> getAll(@RequestHeader("X-AUTH-LOG-HEADER") String token) {
		Application application = applicationService.getUserByToken(token);
		return new ResponseEntity<List<AuditUiLogs>>(service.getTodayAuditLog(application.getId()), HttpStatus.OK);
	}

	@GetMapping(path = "level/{level}")
	public ResponseEntity<List<AuditUiLogs>> getByAppId(@RequestHeader("X-AUTH-LOG-HEADER") String token,
			@PathVariable("level") Level level) {
		Application application = applicationService.getUserByToken(token);
		return new ResponseEntity<List<AuditUiLogs>>(service.getTodayAuditLog(application.getId(), level),
				HttpStatus.OK);
	}

	@GetMapping(path = "level/total/today")
	public ResponseEntity<?> getTodayCountByAppId(@RequestHeader("X-AUTH-LOG-HEADER") String token) {
		Application application = applicationService.getUserByToken(token);
		return new ResponseEntity<Integer>(service.getTodayAuditLogTodayTotalCount(application.getId()), HttpStatus.OK);
	}

	@GetMapping(path = "level/{level}/count")
	public ResponseEntity<?> getCountByAppId(@RequestHeader("X-AUTH-LOG-HEADER") String token,
			@PathVariable("level") Level level) {
		Application application = applicationService.getUserByToken(token);
		return new ResponseEntity<Integer>(service.getTodayAuditLogCount(application.getId(), level), HttpStatus.OK);
	}

	@GetMapping(path = "level/{level}/{past}/count")
	public ResponseEntity<?> getCountByAppIdAndDate(@RequestHeader("X-AUTH-LOG-HEADER") String token,
			@PathVariable("level") Level level, @PathVariable("past") int count) {
		Application application = applicationService.getUserByToken(token);
		Date yesterDay = DateUtility.addDaysToDay(count);
		return new ResponseEntity<Integer>(
				service.getTodayAuditLogCount(application.getId(), level, new Timestamp(yesterDay.getTime())),
				HttpStatus.OK);
	}

	@GetMapping(path = "level/total/{past}")
	public ResponseEntity<?> getCountByAppIdTotalCountAndDate(@RequestHeader("X-AUTH-LOG-HEADER") String token,
			@PathVariable("past") int count) {
		Application application = applicationService.getUserByToken(token);
		Date yesterDay = DateUtility.subtractDaysToDay(count);
		return new ResponseEntity<Integer>(
				service.getTodayAuditLogTotalCount(application.getId(), new Timestamp(yesterDay.getTime())),
				HttpStatus.OK);
	}

	@GetMapping(path = "stat/{past}")
	public ResponseEntity<?> getLastNDayStatus(@RequestHeader("X-AUTH-LOG-HEADER") String token,
			@PathVariable("past") int count) {
		Application application = applicationService.getUserByToken(token);
		return new ResponseEntity<List<LogLevelCount>>(service.getLastNDayLog(application.getId(), count),
				HttpStatus.OK);

	}
	
	@GetMapping(path = "updatedate")
	public ResponseEntity<?> updateDate(@RequestHeader("X-AUTH-LOG-HEADER") String token ) { 
		service.updateDate();
		return new ResponseEntity<Boolean>(Boolean.TRUE,
				HttpStatus.OK);

	}
	
	@GetMapping(path = "search")
	public ResponseEntity<?> search(@RequestHeader("X-AUTH-LOG-HEADER") String token,
			@RequestParam(name = "level") String level,
			@RequestParam(name = "keyword") String keyword,
			@RequestParam(name = "fromDate") String fromDate,
			@RequestParam(name = "toDate") String toDate) { 
		
		Application application = applicationService.getUserByToken(token);
		
		Level level2 = null;
		String searchKeyword = null;
		java.sql.Date searchToDate = null;
		java.sql.Date searchFromDate = null;
		if(!StringUtils.isEmpty(level)) {
			level2 = Level.getByValue(level);
		}		
		if(!StringUtils.isEmpty(keyword)) {
			searchKeyword = keyword;
		}
		if(!StringUtils.isEmpty(fromDate)) {
			searchFromDate = new java.sql.Date( DateUtility.convertToDate("yyyy-MM-dd", fromDate).getTime());
		}
		if(!StringUtils.isEmpty(toDate)) {
			searchToDate = new java.sql.Date( DateUtility.convertToDate("yyyy-MM-dd", toDate).getTime());
		}
		List<AuditUiLogs> auditUiLogs =  service.search(application.getId(), level2 , searchKeyword, searchToDate, searchFromDate);
		
		return new ResponseEntity<List<AuditUiLogs>>(auditUiLogs,
				HttpStatus.OK);

	}

}
