package de.pius.cookshare.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final String errorCode; 

    private final HttpStatus status;

    private final LocalDateTime timestamp;

    private final Map<String, Object> details;

    public ApiException(
            String errorCode,
            String message,
            HttpStatus status,
            Map<String, Object> details) {
        super(message);
        this.errorCode = errorCode.replace(" ", "_");
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
