package de.pius.cookshare.DTO.recipeDTO.ingredientDTO;

import de.pius.cookshare.types.IngredientUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateIngredientRequest(
    int amount,

    @NotNull(message = "Unit can not be empty")
    IngredientUnit unit,

    @NotBlank(message = "Ingredient name can not be empty")
    @Size(max = 50, message = "Ingredient name can only be a maximum of 50 letters")
    String name
) {
}
