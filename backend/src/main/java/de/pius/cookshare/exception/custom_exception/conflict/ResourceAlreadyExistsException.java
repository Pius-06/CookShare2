package de.pius.cookshare.exception.custom_exception.conflict;

import java.util.Map;

import org.springframework.http.HttpStatus;

import de.pius.cookshare.exception.ApiException;

public abstract class ResourceAlreadyExistsException extends ApiException {

    protected static final HttpStatus STATUS = HttpStatus.CONFLICT;

    protected ResourceAlreadyExistsException(
            String resourceName,
            String field,
            String value) {

        super(
            resourceName.toUpperCase() + "_ALREADY_EXISTS",
            String.format("%s with %s '%s' already exists", resourceName, field, value),
            STATUS,
            Map.of(field, value)
        );
    }
}