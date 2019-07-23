package com.jitendra.logasservice.es.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.es.model.ESAuditUiLog;
import com.jitendra.logasservice.es.model.ESAuditUiLog.ESAuditUiLogsBuilder;
import com.jitendra.logasservice.es.repository.ESAuditUiLogRepository;
import com.jitendra.logasservice.es.service.ESAuditUiLogService;
import com.jitendra.logasservice.utils.DateUtility;

@Service
public class ESAuditUiLogServiceImpl implements ESAuditUiLogService {

	@Autowired
	private ESAuditUiLogRepository auditUiLogRepository;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Override
	public ESAuditUiLog save(ESAuditUiLog auditUiLog) {
		return auditUiLogRepository.save(auditUiLog);
	}

	@Override
	public void delete(ESAuditUiLog auditUiLog) {
		auditUiLogRepository.delete(auditUiLog);
	}

	@Override
	public ESAuditUiLog findOne(Long id) {
		return auditUiLogRepository.findById(id).get();
	}

	@Override
	public Iterable<ESAuditUiLog> findAll() {
		return auditUiLogRepository.findAll();
	}

	@Override
	public List<ESAuditUiLog> getByAppId(Long appId, PageRequest pageRequest) {
		return auditUiLogRepository.getBy(appId);
	}

	@Override
	public List<ESAuditUiLog> getByAppAndLevel(String appId, Level level) {
		return auditUiLogRepository.getByAppIdAndLevel(appId, level);
	}

	@Override
	public List<ESAuditUiLog> getTodayByAppAndLevel(String appId, Level level) throws JsonProcessingException {

		QueryBuilder rangeQuery = QueryBuilders.rangeQuery("logTime").from(DateUtility.getTodayStartTime().getTime())
				.to(DateUtility.getTodayEndTime().getTime()).includeLower(true).includeUpper(true);

		QueryBuilder termsQuery = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("appId", appId))
				.must(QueryBuilders.matchQuery("level", level.getName()));

		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(termsQuery).filter(rangeQuery);

		SearchResponse searchResponse = esTemplate.getClient().prepareSearch("log").setQuery(queryBuilder).get();
		List<ESAuditUiLog> auditUiLogs = new ArrayList<>();
		SearchHit[] results = searchResponse.getHits().getHits();
		for (SearchHit hit : results) {
			String sourceAsString = hit.getSourceAsString();
			auditUiLogs.add(new ESAuditUiLogsBuilder().getObject(sourceAsString));
		}
		return auditUiLogs;
	}

	@Override
	public List<ESAuditUiLog> getTodayByApp(String appId) throws JsonProcessingException {

		QueryBuilder rangeQuery = QueryBuilders.rangeQuery("logTime").from(DateUtility.getTodayStartTime().getTime())
				.to(DateUtility.getTodayEndTime().getTime()).includeLower(true).includeUpper(true);

		QueryBuilder mathchQuery = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("appId", appId));

		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(mathchQuery).filter(rangeQuery);

		SearchResponse searchResponse = esTemplate.getClient().prepareSearch("log").setQuery(queryBuilder).get();

		List<ESAuditUiLog> auditUiLogs = new ArrayList<>();
		SearchHit[] results = searchResponse.getHits().getHits();
		for (SearchHit hit : results) {
			String sourceAsString = hit.getSourceAsString();
			auditUiLogs.add(new ESAuditUiLogsBuilder().getObject(sourceAsString));
		}
		return auditUiLogs;
	}

	@Override
	public List<ESAuditUiLog> getByAppId(String appId) {
		return auditUiLogRepository.getByAppId(appId);
	}

	@Override
	public List<ESAuditUiLog> getByAppAndLevel(String appId, Level level, Timestamp startDate, Timestamp endDate)
			throws JsonProcessingException {
		QueryBuilder rangeQuery = QueryBuilders.rangeQuery("logTime").from(startDate.getTime()).to(endDate.getTime())
				.includeLower(true).includeUpper(true);

		QueryBuilder termsQuery = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("appId", appId))
				.must(QueryBuilders.matchQuery("level", level.getName()));

		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(termsQuery).filter(rangeQuery);

		SearchResponse searchResponse = esTemplate.getClient().prepareSearch("log").setQuery(queryBuilder).get();

		List<ESAuditUiLog> auditUiLogs = new ArrayList<>();
		SearchHit[] results = searchResponse.getHits().getHits();
		for (SearchHit hit : results) {
			String sourceAsString = hit.getSourceAsString();
			auditUiLogs.add(new ESAuditUiLogsBuilder().getObject(sourceAsString));
		}
		return auditUiLogs;
	}

	@Override
	public List<ESAuditUiLog> getByAppAndLevel(String appId, Timestamp startDate, Timestamp endDate)
			throws JsonProcessingException {
		QueryBuilder rangeQuery = QueryBuilders.rangeQuery("logTime").from(startDate.getTime()).to(endDate.getTime())
				.includeLower(true).includeUpper(true);

		QueryBuilder termsQuery = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("appId", appId));

		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(termsQuery).filter(rangeQuery);

		SearchResponse searchResponse = esTemplate.getClient().prepareSearch("log").setQuery(queryBuilder).get();

		List<ESAuditUiLog> auditUiLogs = new ArrayList<>();
		SearchHit[] results = searchResponse.getHits().getHits();
		for (SearchHit hit : results) {
			String sourceAsString = hit.getSourceAsString();
			auditUiLogs.add(new ESAuditUiLogsBuilder().getObject(sourceAsString));
		}
		return auditUiLogs;
	}

}
