package de.pius.cookshare.recipe.recipe;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import de.pius.cookshare.recipe.recipe.dto.RecipeRequestDTO;
import de.pius.cookshare.recipe.recipe.dto.RecipeResponseDTO;
import de.pius.cookshare.recipe.recipe.dto.RecipeSearchRequestDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Page<RecipeResponseDTO> getRecipesByUserId(
            Long userId,
            RecipeSearchRequestDTO dto,
            Pageable pageable) {
        Page<Recipe> recipes = recipeRepository.findPublicRecipesByAuthorIdAndFilter(
            userId, 
            dto.title(),
            dto.category(),
            dto.difficulty(),
            pageable);
        return recipes.map(recipe -> RecipeResponseDTO.from(recipe));
    }

    public Page<RecipeResponseDTO> getOwnRecipes(
            Long userId,
            RecipeSearchRequestDTO dto,
            Pageable pageable) {
        Page<Recipe> recipes = recipeRepository.findByAuthorIdAndFilter(
            userId, 
            dto.title(),
            dto.category(),
            dto.difficulty(),
            pageable);
        return recipes.map(recipe -> RecipeResponseDTO.from(recipe));
    }

    public Page<RecipeResponseDTO> getAllRecipes(
            RecipeSearchRequestDTO dto,
            Pageable pageable) {
        Page<Recipe> recipes = recipeRepository.findAllByFilter(
            dto.title(),
            dto.category(),
            dto.difficulty(),
            pageable);
        return recipes.map(recipe -> RecipeResponseDTO.from(recipe));
    }

    public RecipeResponseDTO createRecipe(RecipeRequestDTO dto) {
        Recipe recipe = RecipeMapper.toRecipe(dto);
        return RecipeResponseDTO.from(recipeRepository.save(recipe));
    }
}
