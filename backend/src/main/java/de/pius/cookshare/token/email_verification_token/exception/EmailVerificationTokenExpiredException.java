package de.pius.cookshare.token.email_verification_token.exception;

import org.springframework.http.HttpStatus;

import de.pius.cookshare.exception.ApiException;

public class EmailVerificationTokenExpiredException extends ApiException {

    private static final String ERROR_CODE = "VERIFICATION_TOKEN_EXPIRED";

    private static final HttpStatus STATUS = HttpStatus.GONE;

    public EmailVerificationTokenExpiredException() {
        super(
                ERROR_CODE,
                "Verification token has expired",
                STATUS, 
                null);
    }
}