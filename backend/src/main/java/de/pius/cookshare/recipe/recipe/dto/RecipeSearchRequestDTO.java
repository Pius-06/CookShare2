package de.pius.cookshare.recipe.recipe.dto;

import de.pius.cookshare.recipe.recipe.Category;
import de.pius.cookshare.recipe.recipe.Difficulty;

public record RecipeSearchRequestDTO (
    String title,

    Category category,

    Difficulty difficulty
) {
    
}
