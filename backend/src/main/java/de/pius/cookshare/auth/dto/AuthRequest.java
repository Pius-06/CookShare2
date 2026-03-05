package de.pius.cookshare.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    
    @NotBlank(message = "Email can not be empty")
    String email,

    @NotBlank(message = "Password can not be empty")
    String password
) {
    
}
