package de.pius.cookshare.exception.handler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import de.pius.cookshare.exception.ApiException;
import de.pius.cookshare.exception.ErrorResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String VALIDATION_ERROR_CODE = "VALIDATION_ERROR";


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex, WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.of(ex, request.getDescription(false));

        return ResponseEntity
                .status(ex.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        Map<String, Object> fieldErrors = getFieldErrors(ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(ex.getStatusCode().value())
                .error("Validation failed")
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .errorCode(VALIDATION_ERROR_CODE)
                .details(fieldErrors)
                .build();

        return ResponseEntity
                .status(ex.getStatusCode().value())
                .body(errorResponse);
    }

    private Map<String, Object> getFieldErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(error -> error.getField(),
                        error -> error.getDefaultMessage()));
        return fieldErrors;
    }

}
