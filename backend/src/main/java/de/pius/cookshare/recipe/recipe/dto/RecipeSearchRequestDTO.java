package de.pius.cookshare.recipe.recipe.dto;

import java.util.Locale.Category;

import de.pius.cookshare.recipe.recipe.Difficulty;

public record RecipeSearchRequestDTO (
    String title,

    Category category,

    Difficulty difficulty
) {
    
}
