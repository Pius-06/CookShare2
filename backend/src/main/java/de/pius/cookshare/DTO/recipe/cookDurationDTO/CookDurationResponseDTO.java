package de.pius.cookshare.dto.recipe.cookDurationDTO;

import de.pius.cookshare.model.recipe.CookDuration;
import de.pius.cookshare.types.DurationUnit;

public record CookDurationResponseDTO(
        int amount,
        
        DurationUnit unit
) {
    public static CookDurationResponseDTO from(CookDuration duration) {
        if (duration == null) return null;
        return new CookDurationResponseDTO(
                duration.getAmount(),
                duration.getUnit()
        );
    }
}
