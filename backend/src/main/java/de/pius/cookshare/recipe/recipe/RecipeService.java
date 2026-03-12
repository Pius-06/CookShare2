package de.pius.cookshare.recipe.recipe;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import de.pius.cookshare.recipe.recipe.dto.RecipeResponseDTO;
import de.pius.cookshare.recipe.recipe.dto.RecipeSearchRequestDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Page<RecipeResponseDTO> getRecipesByUserId(Long userId, Pageable pageable) {
        Page<Recipe> recipes = recipeRepository.findPublicRecipesByAuthorId(userId);
        return recipes.map(recipe -> RecipeResponseDTO.from(recipe));
    }

    public Page<RecipeResponseDTO> getOwnRecipes(Long userId, Pageable pageable) {
        Page<Recipe> recipes = recipeRepository.findByAuthorId(userId);
        return recipes.map(recipe -> RecipeResponseDTO.from(recipe));
    }

    public Page<RecipeResponseDTO> getAllRecipes(
            RecipeSearchRequestDTO dto,
            Pageable pageable) {
        Page<Recipe> recipes = recipeRepository.findAll(pageable);
        return recipes.map(recipe -> RecipeResponseDTO.from(recipe));
    }
}
