package com.jitendra.logasservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jitendra.logasservice.model.AuditUiLogs;


@Repository
public interface AuditUiLogsRepository extends JpaRepository<AuditUiLogs, Long> {

}
