package com.Jovac_Project.Global_Guard_API_error_mangement.controller;

import com.Jovac_Project.Global_Guard_API_error_mangement.entity.ErrorLog;
import com.Jovac_Project.Global_Guard_API_error_mangement.repository.ErrorLogRepository;
import com.Jovac_Project.Global_Guard_API_error_mangement.exception.ApiResourceNotFoundException;
import com.Jovac_Project.Global_Guard_API_error_mangement.exception.InvalidApiRequestException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ErrorLogRepository errorLogRepository;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        logErrorToDatabase(ex, HttpStatus.NOT_FOUND, request);
        return buildResponseEntity("Resource not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiResourceNotFoundException.class)
    public ResponseEntity<Object> handleApiResourceNotFound(ApiResourceNotFoundException ex, HttpServletRequest request) {
        logErrorToDatabase(ex, HttpStatus.NOT_FOUND, request);
        return buildResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidApiRequestException.class)
    public ResponseEntity<Object> handleInvalidApiRequest(InvalidApiRequestException ex, HttpServletRequest request) {
        logErrorToDatabase(ex, HttpStatus.BAD_REQUEST, request);
        return buildResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        logErrorToDatabase(ex, HttpStatus.BAD_REQUEST, request);
        return buildResponseEntity("Invalid argument: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, HttpServletRequest request) {
        logErrorToDatabase(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
        return buildResponseEntity("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> buildResponseEntity(String errorMessage, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", errorMessage);
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        return new ResponseEntity<>(body, status);
    }

    private void logErrorToDatabase(Exception ex, HttpStatus status, HttpServletRequest request) {
        try {
            String stackTrace = getStackTraceAsString(ex);
            String requestUrl = request.getRequestURL().toString();
            String httpMethod = request.getMethod();

            ErrorLog errorLog = new ErrorLog(
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                stackTrace,
                status.value(),
                requestUrl,
                httpMethod
            );

            errorLogRepository.save(errorLog);
        } catch (Exception e) {
            System.err.println("Failed to log error to database: " + e.getMessage());
        }
    }

    private String getStackTraceAsString(Exception ex) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}
