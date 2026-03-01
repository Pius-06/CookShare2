package de.pius.cookshare.token.email_verification_token.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import de.pius.cookshare.exception.ApiException;

public class VerificationTokenAlreadyUsed extends ApiException {

    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    private static final String ERROR_CODE = "VERIFICATION_TOKEN_ALREADY_USED";

    public VerificationTokenAlreadyUsed() {
        super(
            ERROR_CODE, 
            "verification token is already used", 
            STATUS, 
            null);
    }

}
