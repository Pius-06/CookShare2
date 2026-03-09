package de.pius.cookshare.token.email_verification_token.exception;

import de.pius.cookshare.exception.custom_exception.not_found.RessourceNotFoundException;

public class EmailVerificationTokenNotFoundException extends RessourceNotFoundException {

    public EmailVerificationTokenNotFoundException(String field, String value) {
        super("email verification token", field, value);
    }

}
