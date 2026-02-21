package de.pius.cookshare.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.DTO.recipeDTO.RecipeRequestDTO;
import de.pius.cookshare.model.Recipe;

public class RecipeMapper {

    public static Recipe toRecipe(RecipeRequestDTO dto) {
        return Recipe.builder()
                .id(dto.authorId())
                .title(dto.title())
                .introduction(dto.introduction())
                .preparation(dto.preparation())
                .recipeImage(ImageMapper.toImage(dto.recipeImage()))
                .duration(CookDurationMapper.toCookDuration(dto.duration()))
                .ingridients(IngredientMapper.toIngredient(dto.ingredients()))
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
