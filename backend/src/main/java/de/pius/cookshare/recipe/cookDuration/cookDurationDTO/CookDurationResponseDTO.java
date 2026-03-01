package de.pius.cookshare.recipe.cookDuration.cookDurationDTO;

import de.pius.cookshare.recipe.cookDuration.CookDuration;
import de.pius.cookshare.recipe.cookDuration.DurationUnit;

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
