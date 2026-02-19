package de.pius.cookshare.DTO.recipeDTO.ingredientDTO;

import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.model.Ingredient;
import de.pius.cookshare.types.IngredientUnit;

public record IngridientResponseDTO(

        int amount,

        IngredientUnit unit,

        String name) {

    public static IngridientResponseDTO from(Ingredient ingredient) {
        return new IngridientResponseDTO(
                ingredient.getAmount(),
                ingredient.getUnit(),
                ingredient.getName());
    }

    public static Set<IngridientResponseDTO> from(Set<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> from(ingredient))
                .collect(Collectors.toSet());
    }
}
