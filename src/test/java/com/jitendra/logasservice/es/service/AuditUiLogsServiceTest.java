package com.jitendra.logasservice.es.service;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
		List<AuditUiLogs> logs = service.search("TE9HQVNTRVJWSUNF-FSZVIRT54B1562438203721", Level.INFO, "JITENDRA",
				new Date( DateUtility.convertToDate("yyyy-MM-dd", "2019-07-25").getTime()), 
				new Date( DateUtility.convertToDate("yyyy-MM-dd", "2019-07-20").getTime()));
		System.out.println(logs);
	}

}
