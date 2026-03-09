package de.pius.cookshare.exception.custom_exception.unauthorized;

import org.springframework.http.HttpStatus;

import de.pius.cookshare.exception.ApiException;

public class UnauthorizedException extends ApiException {

    protected static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;

    protected UnauthorizedException(
            String errorCode,
            String message) {
        super(
            errorCode,
            message,
            STATUS,
            null
        );
    }
}
