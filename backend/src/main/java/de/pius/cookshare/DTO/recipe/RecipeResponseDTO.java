package de.pius.cookshare.dto.recipe;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.dto.recipe.ingredientDTO.IngredientResponseDTO;
import de.pius.cookshare.dto.user.UserResponseDTO;
import de.pius.cookshare.model.recipe.CookDuration;
import de.pius.cookshare.model.recipe.Recipe;
import de.pius.cookshare.types.Category;
import de.pius.cookshare.types.Difficulty;

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
                .collect(Collectors.toSet()); // ???
    }
}
