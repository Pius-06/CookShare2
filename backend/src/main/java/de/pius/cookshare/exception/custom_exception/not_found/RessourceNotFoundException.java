package de.pius.cookshare.exception.custom_exception.not_found;

import java.util.Map;

import org.springframework.http.HttpStatus;

import de.pius.cookshare.exception.ApiException;

public class RessourceNotFoundException extends ApiException {

    protected static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    protected RessourceNotFoundException(
            String resourceName,
            String field,
            String value) {

        super(
            resourceName.toUpperCase() + "_NOT_FOUND",
            String.format("%s with %s '%s' not found", resourceName, field, value),
            STATUS,
            Map.of(field, value)
        );
    }
}
