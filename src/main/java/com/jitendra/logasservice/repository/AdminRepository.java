package com.jitendra.logasservice.repository;

import com.jitendra.logasservice.model.Admin;
import com.jitendra.logasservice.model.AuditUiLogs;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends PagingAndSortingRepository<Admin, Long> {

    @Query("SELECT A FROM Admin A WHERE A.emailId = :email AND A.password = :password AND A.isActive = TRUE")
    public Admin get(String email,String password);

}
