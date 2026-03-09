package de.pius.cookshare.user.exception.password;

import de.pius.cookshare.exception.custom_exception.unauthorized.UnauthorizedException;

public class OldPasswordMismatchException extends UnauthorizedException {
    
    public OldPasswordMismatchException() {
        super(
            "INVALID_CURRENT_PASSWORD",
            "The current password provided is incorrect."
        );
    }
}
