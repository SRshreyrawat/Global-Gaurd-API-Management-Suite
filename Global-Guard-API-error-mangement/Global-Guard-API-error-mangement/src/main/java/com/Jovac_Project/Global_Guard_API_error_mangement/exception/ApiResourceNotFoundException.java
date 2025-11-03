package com.Jovac_Project.Global_Guard_API_error_mangement.exception;

public class ApiResourceNotFoundException extends RuntimeException {
    public ApiResourceNotFoundException(String message) {
        super(message);
    }
    
    public ApiResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
