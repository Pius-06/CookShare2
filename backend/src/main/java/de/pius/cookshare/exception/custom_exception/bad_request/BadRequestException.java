package de.pius.cookshare.exception.custom_exception.bad_request;

import org.springframework.http.HttpStatus;

import de.pius.cookshare.exception.ApiException;

public class BadRequestException extends ApiException {

    protected static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException(
        String errorCode, 
        String message) {
        super(
            errorCode, 
            message, 
            STATUS, 
            null);
    }
    
}
