package com.Jovac_Project.Global_Guard_API_error_mangement.repository;

import com.Jovac_Project.Global_Guard_API_error_mangement.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {

    
}

