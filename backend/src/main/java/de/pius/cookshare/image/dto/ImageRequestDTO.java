package de.pius.cookshare.image.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ImageRequestDTO(

    @NotBlank(message = "OriginalName can not be empty")
    @Size(max = 50, message = "originalName can only be a maximum of 50 letters")
    String originalName,

    @NotBlank(message = "ContentType can not be empty")
    @Size(max = 50, message = "ContentType can only be a maximum of 50 letters")
    String contentType
) { 
}
