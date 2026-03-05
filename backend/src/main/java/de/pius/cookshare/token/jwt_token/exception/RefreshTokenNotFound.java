package de.pius.cookshare.token.jwt_token.exception;

import de.pius.cookshare.exception.custom_exception.not_found.RessourceNotFoundException;

public class RefreshTokenNotFound extends RessourceNotFoundException{

    public RefreshTokenNotFound(
        String field, 
        String value) {
        super("Refresh_Token", field, value);
    }
    
}
