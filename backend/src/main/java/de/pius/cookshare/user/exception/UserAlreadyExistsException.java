package de.pius.cookshare.user.exception;

import de.pius.cookshare.exception.custom_exception.conflict.ResourceAlreadyExistsException;

public class UserAlreadyExistsException extends ResourceAlreadyExistsException {

    public UserAlreadyExistsException(String field, String value) {
        super("User", field, value);
    }
}