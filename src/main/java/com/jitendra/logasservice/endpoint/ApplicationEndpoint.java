package com.jitendra.logasservice.endpoint;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jitendra.logasservice.model.Application;
import com.jitendra.logasservice.service.impl.ApplicationServiceImpl;
import com.jitendra.logasservice.token.Base64BasicEncryption;
import com.jitendra.logasservice.token.SecureTokenGenerator;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/authentication/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ApplicationEndpoint {

	private final Logger logger = LoggerFactory.getLogger(ApplicationEndpoint.class);

	@Autowired
	private ApplicationServiceImpl service;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addApplication(@RequestBody Application application) {
		try {
			if (application != null) {
				application.setId(SecureTokenGenerator.nextAppId("LOGASSERVICE"));
				application.setAccess("ADMIN");
				application.setDescription(application.getAppName());
				application.setAccessToken(SecureTokenGenerator.getToken());
				application.setPassword(Base64BasicEncryption.encoding(application.getPassword().trim()));
			}
			return new ResponseEntity<Application>(service.repository().save(application), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}



	@GetMapping()
	public ResponseEntity<?> getApplication(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		try {
			return new ResponseEntity<Application>(
					service.repository().getApplication(email, Base64BasicEncryption.encoding(password)),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage(), e);
			return new ResponseEntity<Exception>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}

}
