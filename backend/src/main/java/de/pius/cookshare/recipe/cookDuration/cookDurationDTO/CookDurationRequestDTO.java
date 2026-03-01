package de.pius.cookshare.recipe.cookDuration.cookDurationDTO;

import de.pius.cookshare.recipe.cookDuration.DurationUnit;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CookDurationRequestDTO(
    @Min(value = 1, message ="Duration amount must be at least 1")
    int amount,

    @NotNull(message = "Unit can not be empty")
    DurationUnit unit
) {
    
}
