package com.jitendra.logasservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jitendra.logasservice.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
	
	@Query("SELECT A FROM Application A WHERE A.accessToken = :accessToken")
	public Application getActive(@Param("accessToken") String accessToken );

}
