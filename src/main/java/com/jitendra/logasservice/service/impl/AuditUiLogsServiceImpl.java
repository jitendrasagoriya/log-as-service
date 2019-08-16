package com.jitendra.logasservice.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import com.jitendra.logasservice.model.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jitendra.logasservice.enums.Level;
import com.jitendra.logasservice.model.AuditUiLogs;
import com.jitendra.logasservice.model.LogLevelCount;
import com.jitendra.logasservice.repository.AuditUiLogsRepository;
import com.jitendra.logasservice.service.AuditUiLogsService;
import com.jitendra.logasservice.utils.DateUtility;

@Service
public class AuditUiLogsServiceImpl implements AuditUiLogsService<AuditUiLogsRepository> {

	@Autowired
	private AuditUiLogsRepository repository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public AuditUiLogsRepository repository() {
		return repository;
	}

	@Override
	public List<AuditUiLogs> getAuditLog(Level level) {
		return repository.getByLevel(level);
	}

	@Override
	public List<AuditUiLogs> getAuditLog(String appId, Level level) {
		return repository.getByAppAndLevel(appId, level);
	}

	@Override
	public List<AuditUiLogs> getAuditLog(String appId, Level level, Timestamp startDate, Timestamp endDate) {
		return repository.getTodayByAppAndLevel(appId, level, startDate, endDate);
	}

	@Override
	public List<AuditUiLogs> getTodayAuditLog(String appId, Level level) {
		return repository.getTodayByAppAndLevel(appId, level, DateUtility.getTodayStartTime(),
				DateUtility.getTodayEndTime());
	}

	@Override
	public List<AuditUiLogs> getTodayAuditLog(String appId) {
		return repository.getTodayByAppAndLevel(appId, DateUtility.getTodayStartTime(), DateUtility.getTodayEndTime());
	}

	@Override
	public Integer getTodayAuditLogCount(String appId, Level level) {
		return repository.getTodayByAppAndLevelCount(appId, level, DateUtility.getTodayStartTime(),
				DateUtility.getTodayEndTime());
	}

	@Override
	public Integer getTodayAuditLogCount(String appId, Level level, Timestamp startDate) {
		return repository.getTodayByAppAndLevelCount(appId, level, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(startDate));
	}

	@Override
	public Integer getTodayAuditLogCount(String appId, Level level, Timestamp startDate, Timestamp endDate) {
		return repository.getTodayByAppAndLevelCount(appId, level, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(startDate));
	}

	@Override
	public Integer getTodayAuditLogTotalCount(String appId, Timestamp startDate) {
		int count = 0;
		count += repository.getTodayByAppAndLevelCount(appId, Level.INFO, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(startDate));
		count += repository.getTodayByAppAndLevelCount(appId, Level.DEBUG, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(startDate));
		count += repository.getTodayByAppAndLevelCount(appId, Level.ERROR, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(startDate));
		return count;
	}

	@Override
	public Integer getTodayAuditLogTotalCount(String appId, Timestamp startDate, Timestamp endDate) {
		int count = 0;
		count += repository.getTodayByAppAndLevelCount(appId, Level.INFO, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(endDate));
		count += repository.getTodayByAppAndLevelCount(appId, Level.DEBUG, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(endDate));
		count += repository.getTodayByAppAndLevelCount(appId, Level.ERROR, DateUtility.getTodayStartTime(startDate),
				DateUtility.getTodayEndTime(endDate));
		return count;
	}

	@Override
	public Integer getTodayAuditLogTodayTotalCount(String appId) {
		int count = 0;
		count += repository.getTodayByAppAndLevelCount(appId, Level.INFO,
				DateUtility.getTodayStartTime(new Timestamp(System.currentTimeMillis())),
				DateUtility.getTodayEndTime(new Timestamp(System.currentTimeMillis())));
		count += repository.getTodayByAppAndLevelCount(appId, Level.DEBUG,
				DateUtility.getTodayStartTime(new Timestamp(System.currentTimeMillis())),
				DateUtility.getTodayEndTime(new Timestamp(System.currentTimeMillis())));
		count += repository.getTodayByAppAndLevelCount(appId, Level.ERROR,
				DateUtility.getTodayStartTime(new Timestamp(System.currentTimeMillis())),
				DateUtility.getTodayEndTime(new Timestamp(System.currentTimeMillis())));
		return count;
	}

	@Override
	public List<LogLevelCount> getLastNDayLog(String appId, int days) {
		List<LogLevelCount> logLevelCounts = new ArrayList<>();
		Date currentDate = new Date(System.currentTimeMillis());
		Date fromDate = new Date(
				DateUtility.subtractDays(new java.util.Date(System.currentTimeMillis()), days).getTime());

		String sql = "SELECT LEVEL || '~' || LOGDATE|| '~' || COUNT(*) COUNT FROM AUDITLOG WHERE " + " APPID = :id"
				+ " AND LOGDATE BETWEEN :lastdate AND :currentDate" + " GROUP BY LEVEL,LOGDATE ORDER BY LOGDATE DESC";
		Query q = em.createNativeQuery(sql);
		q.setParameter("id", appId);
		q.setParameter("lastdate", fromDate);
		q.setParameter("currentDate", currentDate);
		List<String> result = q.getResultList();
		if (!result.isEmpty()) {
			for (String object : result) {
				String[] values = object.split("~");
				Date date = new Date(DateUtility.convertToDate("yyyy-mm-dd", values[1]).getTime());
				logLevelCounts.add(new LogLevelCount(Level.values()[Integer.parseInt(values[0])], date,
						Integer.parseInt(values[2])));
				System.out.println(object);
			}
		}
		return logLevelCounts;
	}

	@Override
	public void updateDate() {
		Iterable<AuditUiLogs> iterable = repository.findAll();
		for (AuditUiLogs auditUiLog : iterable) {
			auditUiLog.setLogDate(auditUiLog.getLogTime() != null ? new Date(auditUiLog.getLogTime().getTime()) : null);
			System.out.println(auditUiLog);
			repository.save(auditUiLog);
		}

	}

	@Override
	public List<AuditUiLogs> search(String appId, Level level, String keyword, Date toDate, Date fromDate) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AuditUiLogs> cq = cb.createQuery(AuditUiLogs.class);

		Root<AuditUiLogs> auditUiLog = cq.from(AuditUiLogs.class);
		
		List<Object> filterPredicates = new ArrayList<>();
		
		filterPredicates.add(cb.equal(auditUiLog.get("appId"), appId));
		if (level != null)
			filterPredicates.add( cb.equal(auditUiLog.get("level"), level) );
		if (!StringUtils.isEmpty(keyword))
			filterPredicates.add(cb.like(auditUiLog.get("log"), "%" + keyword + "%"));
		if (toDate != null && fromDate != null)
			filterPredicates.add(cb.between(auditUiLog.get("logDate"), fromDate, toDate));

		cq.where(cb.and((Predicate[]) filterPredicates.toArray(new Predicate[0])));
		
		TypedQuery<AuditUiLogs> query = em.createQuery(cq);
		return query.getResultList();
	}


	@Override
	public Result<AuditUiLogs> search(String appId, Level level, String keyword, Date toDate, Date fromDate, Pageable pageable) {
        Long totalCount = 0L;
        Result<AuditUiLogs> result = new Result<>();
        try{
            totalCount = countSearch( appId, level, keyword, toDate, fromDate);
            result.setTotalCount(totalCount);
        }catch (Exception e){}

        try{
            result.setFirstPage(1);
            long totalPage = totalCount / pageable.getPageSize();
            if( (totalCount%pageable.getPageSize()) != 0) {
                totalPage++;
            }
			result.setCurrentPageNumber(pageable.getPageNumber());
            result.setPageCount(totalPage);
            result.setLastPage(totalPage);
        }catch (Exception e){}

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AuditUiLogs> cq = cb.createQuery(AuditUiLogs.class);
		Root<AuditUiLogs> auditUiLog = cq.from(AuditUiLogs.class);
		List<Object> filterPredicates = new ArrayList<>();
		filterPredicates.add(cb.equal(auditUiLog.get("appId"), appId));
		if (level != null)
			filterPredicates.add( cb.equal(auditUiLog.get("level"), level) );
		if (!StringUtils.isEmpty(keyword))
			filterPredicates.add(cb.like(auditUiLog.get("log"), "%" + keyword + "%"));
		if (toDate != null && fromDate != null)
			filterPredicates.add(cb.between(auditUiLog.get("logDate"), fromDate, toDate));

		cq.where(cb.and((Predicate[]) filterPredicates.toArray(new Predicate[0])));
		cq.orderBy( cb.desc( auditUiLog.get("logTime")));

		TypedQuery<AuditUiLogs> query = em.createQuery(cq);
		query.setFirstResult( (pageable.getPageNumber()-1) * pageable.getPageSize() );
		query.setMaxResults( pageable.getPageSize() );
        result.setList(query.getResultList());
		return result;
	}

    @Override
	public Long countSearch(String appId, Level level, String keyword, Date toDate, Date fromDate) {
        final CriteriaBuilder builder=em.getCriteriaBuilder();
        final CriteriaQuery<Long> countCriteria=builder.createQuery(Long.class);
        Root<AuditUiLogs> auditUiLog = countCriteria.from(AuditUiLogs.class);
        List<Object> filterPredicates = new ArrayList<>();

        filterPredicates.add(builder.equal(auditUiLog.get("appId"), appId));
        if (level != null)
            filterPredicates.add( builder.equal(auditUiLog.get("level"), level) );
        if (!StringUtils.isEmpty(keyword))
            filterPredicates.add(builder.like(auditUiLog.get("log"), "%" + keyword + "%"));
        if (toDate != null && fromDate != null)
            filterPredicates.add(builder.between(auditUiLog.get("logDate"), fromDate, toDate));

        countCriteria.select(builder.count(auditUiLog));
        countCriteria.where(builder.and((Predicate[]) filterPredicates.toArray(new Predicate[0])));
        countCriteria.distinct(countCriteria.isDistinct());
        return em.createQuery(countCriteria).getSingleResult();
	}

}
