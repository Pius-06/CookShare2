package de.pius.cookshare.DTO.userDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequestDTO(

    @NotBlank(message = "Username can not be empty")
    @Size(min = 4, message = "Username must be at least 4 letters long")
    @Size(max = 20, message = "Username can only be a maximum of 20 letters")
    String username, // TODO: validation in Security ob username schon existiert

    @NotBlank(message = "firstname can not be empty")
    @Size(max = 200, message = "firstname can only be a maximum of 200 letters")
    @Pattern(regexp = "^[\\p{L}]+$", message = "firstname can only contain letters")
    String firstname,

    @NotBlank(message = "lastname can not be empty")
    @Size(max = 200, message = "lastname can only be a maximum of 200 letters")
    @Pattern(regexp = "^[\\p{L}]+$", message = "lastname can only contain letters")
    String lastname,

    @NotBlank(message = "email can not be empty")
    @Email(message = "email must conform to the email format")
    String email, // TODO: validation in Security ob email schon existiert

    @NotBlank(message = "password can not be empty")
    @Size(min = 8, message = "password must be at least 8 letters long")
    @Size(max = 200, message = "password can only be a maximum of 200 letters long")
    @Pattern(regexp = """
            ((?=.*[A-Z]){1}
            (?=.*[0-9]){1}
            (?=.*[!@#$%^&*]){1}
            ).+$""", message = "password must contain at least one upercase letter, one number, one special character")
    // ^: Präfix
    // $: Ende des Strings
    // ?=.*: Der String muss irgendwo das Pattern enthalten (?=PATTERN)
    // .* = beliebige Zeichen, 0 oder mehr
    String password
) {
}
