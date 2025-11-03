package com.Jovac_Project.Global_Guard_API_error_mangement.controller;

import com.Jovac_Project.Global_Guard_API_error_mangement.entity.ErrorLog;
import com.Jovac_Project.Global_Guard_API_error_mangement.repository.ErrorLogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ErrorLogController {

    private final ErrorLogRepository errorLogRepository;

    public ErrorLogController(ErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }

    // Save error log manually (via Postman or another service)
    @PostMapping("/log-error")
    public ResponseEntity<ErrorLog> logError(@RequestBody ErrorLog errorLog) {
        if (errorLog.getTimestamp() == null) {
            errorLog.setTimestamp(LocalDateTime.now());
        }
        ErrorLog savedLog = errorLogRepository.save(errorLog);
        return ResponseEntity.ok(savedLog);
    }

    // Fetch all error logs
    @GetMapping("/errors")
    public ResponseEntity<List<ErrorLog>> getAllErrors() {
        return ResponseEntity.ok(errorLogRepository.findAll());
    }
}
