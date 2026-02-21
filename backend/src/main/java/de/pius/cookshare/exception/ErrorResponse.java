package de.pius.cookshare.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Null-Felder nicht anzeigen ???
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String errorCode;
    private Map<String, Object> details;

    // Statische Factory-Methode für einfache Erstellung
    public static ErrorResponse of(ApiException ex, String path) {
        return ErrorResponse.builder()
                .timestamp(ex.getTimestamp())
                .status(ex.getStatus().value())
                .error(ex.getStatus().name())
                .message(ex.getMessage())
                .path(path)
                .errorCode(ex.getErrorCode())
                .details(ex.getDetails())
                .build();
    }
}