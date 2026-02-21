package de.pius.cookshare.exception.custom_exception.not_found;

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
