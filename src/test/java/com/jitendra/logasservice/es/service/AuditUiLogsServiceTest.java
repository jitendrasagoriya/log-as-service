package com.jitendra.logasservice.es.service;

import java.sql.Date;
import java.util.List;

import com.jitendra.logasservice.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.jitendra.logasservice.LogasserviceApplication;
import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.model.AuditUiLogs;
import com.jitendra.logasservice.service.impl.AuditUiLogsServiceImpl;
import com.jitendra.logasservice.utils.DateUtility;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LogasserviceApplication.class)
public class AuditUiLogsServiceTest {

	@Autowired
	private AuditUiLogsServiceImpl service;

	@Test
	public void testSearch() {
		List<AuditUiLogs> logs = service.search("TE9HQVNTRVJWSUNF-FSZVIRT54B1562438203721", null, "",
				null,
				null);
		System.out.println(logs);
	}

	@Test
	public void testSearchPageable() {
		Pageable pageable = new PageRequest(2,10);
		Result<AuditUiLogs> logs = service.search("TE9HQVNTRVJWSUNF-FSZVIRT54B1562438203721", null, "",
				null,
				null,
				pageable);
		System.out.println(logs);
	}

	@Test
	public void testSearchCount() {
		Long logs = service.countSearch("TE9HQVNTRVJWSUNF-FSZVIRT54B1562438203721", null, "",
				null,
				null);
		System.out.println(logs);
	}

}
