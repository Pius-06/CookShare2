package de.pius.cookshare.recipe.ingredient.dto;

import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.recipe.ingredient.Ingredient;
import de.pius.cookshare.recipe.ingredient.IngredientUnit;

public record IngredientResponseDTO(

        int amount,

        IngredientUnit unit,

        String name) {

    public static IngredientResponseDTO from(Ingredient ingredient) {
        return new IngredientResponseDTO(
                ingredient.getAmount(),
                ingredient.getUnit(),
                ingredient.getName());
    }

    public static Set<IngredientResponseDTO> from(Set<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> from(ingredient))
                .collect(Collectors.toSet());
    }
}
