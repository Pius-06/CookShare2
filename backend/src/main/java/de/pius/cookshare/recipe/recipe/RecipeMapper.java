package de.pius.cookshare.recipe.recipe;

import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.image.ImageMapper;
import de.pius.cookshare.recipe.cookDuration.CookDurationMapper;
import de.pius.cookshare.recipe.ingredient.IngredientMapper;
import de.pius.cookshare.recipe.recipe.dto.RecipeRequestDTO;

public class RecipeMapper {

    public static Recipe toRecipe(RecipeRequestDTO dto) {
        return Recipe.builder()
                .id(dto.authorId())
                .title(dto.title())
                .introduction(dto.introduction())
                .preparation(dto.preparation())
                .recipeImage(ImageMapper.toImage(dto.recipeImage()))
                .duration(CookDurationMapper.toCookDuration(dto.duration()))
                .ingredients(IngredientMapper.toIngredient(dto.ingredients()))
                .servings(dto.servings())
                .category(dto.category())
                .difficulty(dto.difficulty())
                .isPublic(dto.isPublic())
                .build();
    }

    public static Set<Recipe> toRecipe(Set<RecipeRequestDTO> dtos) {
        return dtos.stream()
                .map(recipeDto -> toRecipe(recipeDto))
                .collect(Collectors.toSet());
    }
}
