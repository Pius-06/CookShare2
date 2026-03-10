package de.pius.cookshare.recipe.recipe.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.recipe.cookDuration.CookDuration;
import de.pius.cookshare.recipe.ingredient.dto.IngredientResponseDTO;
import de.pius.cookshare.recipe.recipe.Category;
import de.pius.cookshare.recipe.recipe.Difficulty;
import de.pius.cookshare.recipe.recipe.Recipe;
import de.pius.cookshare.user.dto.UserResponseDTO;
import lombok.Builder;

@Builder
public record RecipeResponseDTO(

        Long id,

        String titel,

        String introduction,

        String preperation,

        CookDuration duration,

        Set<IngredientResponseDTO> ingredients,

        int servings,

        Category category,

        Difficulty difficulty,

        boolean isPublic,

        UserResponseDTO author,

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
                IngredientResponseDTO.from(recipe.getIngredients()),
                recipe.getServings(),
                recipe.getCategory(),
                recipe.getDifficulty(),
                recipe.isPublic(),
                UserResponseDTO.from(recipe.getAuthor()),
                recipe.getCountLikes(),
                recipe.getCreatedAt());
    }

    public static Set<RecipeResponseDTO> from(Set<Recipe> recipes) {
        return recipes.stream()
                .map(recipe -> from(recipe))
                .collect(Collectors.toSet());
    }
}
