package com.jitendra.logasservice.es.service;

import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jitendra.logasservice.LogasserviceApplication;
import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.es.model.ESAuditUiLog;
import com.jitendra.logasservice.es.model.ESAuditUiLog.ESAuditUiLogsBuilder;
import com.jitendra.logasservice.token.SecureTokenGenerator;
import com.jitendra.logasservice.utils.DateUtility;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LogasserviceApplication.class)
public class ESAuditUiLogServiceTest {

	@Autowired
	private ESAuditUiLogService logService;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Before
	public void before() {
		esTemplate.deleteIndex(ESAuditUiLog.class);
		esTemplate.createIndex(ESAuditUiLog.class);
		esTemplate.putMapping(ESAuditUiLog.class);
		esTemplate.refresh(ESAuditUiLog.class);
	}

	public void testSave() {

		ESAuditUiLog auditUiLog = logService
				.save(new ESAuditUiLogsBuilder(new Long(1l), new Timestamp(System.currentTimeMillis()), "My first log",
						Level.INFO, SecureTokenGenerator.nextAppId("andolan")).build());
		assertNotNull(auditUiLog.getId());

	}

	public void testGetByAppIdr() {

		List<ESAuditUiLog> esLogs = new ArrayList<>();

		esLogs.add(new ESAuditUiLog(1001l, new Timestamp(System.currentTimeMillis()), "This is info log 1", Level.INFO,
				"QU5ET0XBTG-PC7968Z6KI1553247020291"));
		esLogs.add(new ESAuditUiLog(1002l, new Timestamp(System.currentTimeMillis()), "This is info log 2", Level.INFO,
				"QU5ET0XBTG-PC7968Z6KI1553247020291"));
		esLogs.add(new ESAuditUiLog(1003l, new Timestamp(System.currentTimeMillis()), "This is error log ", Level.ERROR,
				"QU5ET0XBTG-PC7968Z6KI1553247020291"));
		esLogs.add(new ESAuditUiLog(1004l, new Timestamp(System.currentTimeMillis()), "This is debug log ", Level.DEBUG,
				"QU5ET0XBTG-PC7968Z6KI1553247020291"));

		esLogs.add(new ESAuditUiLog(1005l, new Timestamp(System.currentTimeMillis()), "This is info log 1", Level.INFO,
				"QU5ET0XBLVVJ-44GVPTWJ2Q1553252852180"));
		esLogs.add(new ESAuditUiLog(1006l, new Timestamp(System.currentTimeMillis()), "This is info log 2", Level.INFO,
				"QU5ET0XBLVVJ-44GVPTWJ2Q1553252852180"));
		esLogs.add(new ESAuditUiLog(1007l, new Timestamp(System.currentTimeMillis()), "This is error log ", Level.ERROR,
				"QU5ET0XBLVVJ-44GVPTWJ2Q1553252852180"));
		esLogs.add(new ESAuditUiLog(1008l, new Timestamp(System.currentTimeMillis()), "This is debug log ", Level.DEBUG,
				"QU5ET0XBLVVJ-44GVPTWJ2Q1553252852180"));

		for (ESAuditUiLog auditUiLog : esLogs) {
			logService.save(auditUiLog);
		}

		Iterable<ESAuditUiLog> iterable = logService.getByAppAndLevel("QU5ET0XBTG-PC7968Z6KI1553247020291", Level.INFO);
		iterable.forEach(esAuditUiLog -> {
			System.out.println(esAuditUiLog);
		});

	}

	@Test
	public void testTodayByAppId() {

		List<ESAuditUiLog> esLogs = new ArrayList<>();

		esLogs.add(new ESAuditUiLog(1001l, new Timestamp(System.currentTimeMillis()), "This is info log 1", Level.INFO,
				"QU5ET0XBTG-PC7968Z6KI1553247020291"));
		esLogs.add(new ESAuditUiLog(1002l, new Timestamp(DateUtility.subtractDays(new Date(), 1).getTime()),
				"This is info log 2", Level.INFO, "QU5ET0XBTG-PC7968Z6KI1553247020291"));
		esLogs.add(new ESAuditUiLog(1003l, new Timestamp(System.currentTimeMillis()), "This is error log ", Level.ERROR,
				"QU5ET0XBTG-PC7968Z6KI1553247020291"));
		esLogs.add(new ESAuditUiLog(1004l, new Timestamp(System.currentTimeMillis()), "This is debug log ", Level.DEBUG,
				"QU5ET0XBTG-PC7968Z6KI1553247020291"));

		esLogs.add(new ESAuditUiLog(1001l, new Timestamp(System.currentTimeMillis()), "This is info log 1", Level.INFO,
				"QU5ET0XBLVVJ-44GVPTWJ2Q1553252852180"));
		esLogs.add(new ESAuditUiLog(1002l, new Timestamp(DateUtility.subtractDays(new Date(), 1).getTime()),
				"This is info log 2", Level.INFO, "QU5ET0XBLVVJ-44GVPTWJ2Q1553252852180"));
		esLogs.add(new ESAuditUiLog(1003l, new Timestamp(System.currentTimeMillis()), "This is error log ", Level.ERROR,
				"QU5ET0XBLVVJ-44GVPTWJ2Q1553252852180"));
		esLogs.add(new ESAuditUiLog(1004l, new Timestamp(System.currentTimeMillis()), "This is debug log ", Level.DEBUG,
				"QU5ET0XBLVVJ-44GVPTWJ2Q1553252852180"));

		for (ESAuditUiLog auditUiLog : esLogs) {
			logService.save(auditUiLog);
		}

		Iterable<ESAuditUiLog> iterable;
		try {
			iterable = logService.getTodayByAppAndLevel("QU5ET0XBTG-PC7968Z6KI1553247020291",Level.INFO);
			iterable.forEach(esAuditUiLog -> {
				System.out.println(esAuditUiLog);
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

	@After
	public void close() {
		esTemplate.getClient().close();
	}

}
