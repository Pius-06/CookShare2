package de.pius.cookshare.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(

    @Size(min = 4, message = "Username must be at least 4 letters long")
    @Size(max = 20, message = "Username can only be a maximum of 20 letters")
    String username, 

    @Size(max = 200, message = "Firstname can only be a maximum of 200 letters")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Firstname can only contain letters")
    String firstname,

    @Size(max = 200, message = "Lastname can only be a maximum of 200 letters")
    @Pattern(regexp = "^[\\p{L}]+$", message = "Lastname can only contain letters")
    String lastname,

    @Email(message = "Email must conform to the email format")
    String email,

    @Size(max = 200, message = "Bio can only be a maximum of 200 letters long")
    String bio
) {
    
}
