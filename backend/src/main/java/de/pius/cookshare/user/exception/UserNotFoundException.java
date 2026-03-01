package de.pius.cookshare.user.exception;

import de.pius.cookshare.exception.custom_exception.not_found.RessourceNotFoundException;

public class UserNotFoundException extends RessourceNotFoundException {

    public UserNotFoundException(
            String field,
            String value) {
        super(
                "User",
                field,
                value);
    }

}
