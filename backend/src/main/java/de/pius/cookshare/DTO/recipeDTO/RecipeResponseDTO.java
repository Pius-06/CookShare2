package de.pius.cookshare.DTO.recipeDTO;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.model.CookDuration;
import de.pius.cookshare.model.Ingredient;
import de.pius.cookshare.model.Recipe;
import de.pius.cookshare.model.User;
import de.pius.cookshare.types.Category;
import de.pius.cookshare.types.Difficulty;

public record RecipeResponseDTO(
        Long id,

        String titel,

        String introduction,

        String preperation,

        CookDuration duration,

        Set<Ingredient> ingredients,

        int servings,

        Category category,

        Difficulty difficulty,

        boolean isPublic,

        User author,

        int countLikes,

        LocalDateTime createdAt
) {
    public static RecipeResponseDTO from(Recipe recipe) {
        return new RecipeResponseDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getIntroduction(),
                recipe.getPreparation(),
                recipe.getDuration(),
                recipe.getIngridients(),
                recipe.getServings(),
                recipe.getCategory(),
                recipe.getDifficulty(),
                recipe.isPublic(),
                recipe.getAuthor(),
                recipe.getCountLikes(),
                recipe.getCreatedAt());
    }

    public static Set<RecipeResponseDTO> from(Set<Recipe> recipes) {
        return recipes.stream()
                .map(recipe -> from(recipe))
                .collect(Collectors.toSet()); // ???
    }
}
