package de.pius.cookshare.DTO.recipeDTO.CookDurationDTO;

import de.pius.cookshare.types.DurationUnit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateCookDurationRequestDTO(
    @Min(value = 1, message ="Duration amount must be at least 1")
    int amount,

    @NotNull(message = "Unit can not be empty")
    DurationUnit unit
) {
    
}
