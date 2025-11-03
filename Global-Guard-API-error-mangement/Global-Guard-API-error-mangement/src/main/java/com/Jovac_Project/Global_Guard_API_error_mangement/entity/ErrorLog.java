package com.Jovac_Project.Global_Guard_API_error_mangement.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "error_logs")
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String errorMessage;

    @Column(nullable = false)
    private String exceptionType;

    @Column(columnDefinition = "TEXT")  // Changed to TEXT for longer stack traces
    private String stackTrace;

    @Column(nullable = false)
    private Integer httpStatus;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column
    private String requestUrl;

    @Column
    private String httpMethod;

    // Constructors remain the same
    public ErrorLog() {}

    public ErrorLog(String errorMessage, String exceptionType, String stackTrace, 
                    Integer httpStatus, String requestUrl, String httpMethod) {
        this.errorMessage = errorMessage;
        this.exceptionType = exceptionType;
        this.stackTrace = stackTrace;
        this.httpStatus = httpStatus;
        this.requestUrl = requestUrl;
        this.httpMethod = httpMethod;
        this.timestamp = LocalDateTime.now();
    }

    // All getters and setters remain the same
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public String getExceptionType() { return exceptionType; }
    public void setExceptionType(String exceptionType) { this.exceptionType = exceptionType; }

    public String getStackTrace() { return stackTrace; }
    public void setStackTrace(String stackTrace) { this.stackTrace = stackTrace; }

    public Integer getHttpStatus() { return httpStatus; }
    public void setHttpStatus(Integer httpStatus) { this.httpStatus = httpStatus; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getRequestUrl() { return requestUrl; }
    public void setRequestUrl(String requestUrl) { this.requestUrl = requestUrl; }

    public String getHttpMethod() { return httpMethod; }
    public void setHttpMethod(String httpMethod) { this.httpMethod = httpMethod; }
}
