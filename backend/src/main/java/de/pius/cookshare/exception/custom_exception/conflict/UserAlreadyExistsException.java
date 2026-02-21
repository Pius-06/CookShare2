package de.pius.cookshare.exception.custom_exception.conflict;

public class UserAlreadyExistsException extends ResourceAlreadyExistsException {

    public UserAlreadyExistsException(String field, String value) {
        super("User", field, value);
    }
}