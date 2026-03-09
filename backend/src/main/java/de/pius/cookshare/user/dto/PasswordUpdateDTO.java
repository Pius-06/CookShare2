package de.pius.cookshare.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PasswordUpdateDTO(

        @NotBlank(message = "Old password can not be empty") 
        String oldPassword,

        @NotBlank(message = "New password can not be empty") 
        @Size(min = 8, message = "Password must be at least 8 letters long") 
        @Size(max = 200, message = "Password can only be a maximum of 200 letters long") 
        @Pattern(regexp = """
                ((?=.*[A-Z]){1}
                (?=.*[0-9]){1}
                (?=.*[!@#$%^&*]){1}
                ).+$""", message = "Password must contain at least one upercase letter, one number, one special character")
        String newPassword
) {

}
