package de.pius.cookshare.exception.custom_exception.not_found;

public class VerificationTokenNotFoundException extends RessourceNotFoundException {

    public VerificationTokenNotFoundException(String field, String value) {
        super("verification token", field, value);
    }

}
