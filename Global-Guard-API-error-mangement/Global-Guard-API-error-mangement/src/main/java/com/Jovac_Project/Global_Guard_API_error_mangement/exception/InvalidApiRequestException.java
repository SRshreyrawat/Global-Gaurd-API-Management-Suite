package com.Jovac_Project.Global_Guard_API_error_mangement.exception;

public class InvalidApiRequestException extends RuntimeException {
    public InvalidApiRequestException(String message) {
        super(message);
    }
    
    public InvalidApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
