package de.pius.cookshare.email.exception;

import org.springframework.http.HttpStatus;

import de.pius.cookshare.exception.ApiException;

public class EmailNotVerifiedException extends ApiException {

    private static final HttpStatus STATUS = HttpStatus.FORBIDDEN;

    private static final String ERROR_CODE = "EMAIL_NOT_VERIFIED";

    public EmailNotVerifiedException() {
        super(
                ERROR_CODE,
                "Email is not verified",
                STATUS,
                null);
    }
}
