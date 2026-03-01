package de.pius.cookshare.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

    @NotBlank(message = "Username can not be empty")
    @Size(min = 4, message = "Username must be at least 4 letters long")
    @Size(max = 20, message = "Username can only be a maximum of 20 letters")
    String username, 

    @NotBlank(message = "Firstname can not be empty")
    @Size(max = 200, message = "Firstname can only be a maximum of 200 letters")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Firstname can only contain letters")
    String firstname,

    @NotBlank(message = "Lastname can not be empty")
    @Size(max = 200, message = "Lastname can only be a maximum of 200 letters")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Lastname can only contain letters")
    String lastname,

    @NotBlank(message = "Email can not be empty")
    @Email(message = "Email must conform to the email format")
    String email, 

    @NotBlank(message = "Password can not be empty")
    @Size(min = 8, message = "Password must be at least 8 letters long")
    @Size(max = 200, message = "Password can only be a maximum of 200 letters long")
    @Pattern(regexp = """
            ((?=.*[A-Z]){1}
            (?=.*[0-9]){1}
            (?=.*[!@#$%^&*]){1}
            ).+$""", message = "Password must contain at least one upercase letter, one number, one special character")
    // ^: Präfix
    // $: Ende des Strings
    // ?=.*: Der String muss irgendwo das Pattern enthalten (?=PATTERN)
    // .* = beliebige Zeichen, 0 oder mehr
    String password,

    @Size(max = 200, message = "Bio can only be a maximum of 200 letters long")
    String bio
) {
}
