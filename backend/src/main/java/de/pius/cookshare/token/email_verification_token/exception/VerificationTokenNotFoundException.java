package de.pius.cookshare.token.email_verification_token.exception;

import de.pius.cookshare.exception.custom_exception.not_found.RessourceNotFoundException;

public class VerificationTokenNotFoundException extends RessourceNotFoundException {

    public VerificationTokenNotFoundException(String field, String value) {
        super("verification token", field, value);
    }

}
