package de.pius.cookshare.user.exception.password;

import de.pius.cookshare.exception.custom_exception.bad_request.BadRequestException;

public class PasswordSameAsOldException extends BadRequestException {
    public PasswordSameAsOldException() {
        super(
            "PASSWORD_REUSE_FORBIDDEN",
            "The new password cannot be the same as your current password."
        );
    }
}
