package de.pius.cookshare.DTO.userDTO;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserRequestDTO(
    @Size(min = 4, message = "Username must be at least 4 letters long")
    @Size(max = 20, message = "Username can only be a maximum of 20 letters")
    String username, // TODO: validation in Security ob username schon existiert

    @Size(min = 1, message = "Firstname must be at least 1 letter long")
    @Size(max = 200, message = "Firstname can only be a maximum of 200 letters")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Firstname can only contain letters")
    String firstname,

    @Size(min = 1, message = "Lastname must be at least 1 letter long")
    @Size(max = 200, message = "Lastname can only be a maximum of 200 letters")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Lastname can only contain letters")
    String lastname,

    @Size(min = 1, message = "Email must be at least 1 letter long")
    @Email(message = "Email must conform to the email format")
    String email, // TODO: validation in Security ob email schon existiert

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

    @Size(max = 500, message = "Bio can only be a maximum of 500 letters long")
    String bio
) {
    @AssertTrue(message = "At least one field must be set")
    public boolean isEmpty() {
        return username == null &&
               firstname == null &&
               lastname == null &&
               email == null &&
               password == null &&
               bio == null;
    }
}
