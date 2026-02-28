package de.pius.cookshare.dto.recipe;

import java.util.Set;

import de.pius.cookshare.dto.image.ImageRequestDTO;
import de.pius.cookshare.dto.recipe.cookDurationDTO.CookDurationRequestDTO;
import de.pius.cookshare.dto.recipe.ingredientDTO.IngredientRequestDTO;
import de.pius.cookshare.types.Category;
import de.pius.cookshare.types.Difficulty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RecipeRequestDTO(
    
    @NotNull(message = "Author id can not be empty")
    Long authorId,
    
    @NotBlank(message = "Title can not be empty")
    @Size(max = 50, message = "Title can only be a maximum of 50 letters long")
    String title,

    @NotBlank(message = "Introduction can not be empty")
    @Size(max = 100, message = "Introduction can only be a maximum of 100 letters long")
    String introduction,

    @NotBlank(message = "Preparation can not be empty")
    @Size(max = 2000, message = "Preparation can only be a maximum of 2000 letters long")
    String preparation,

    ImageRequestDTO recipeImage,

    @NotNull(message = "Duration can not be empty")
    CookDurationRequestDTO duration,

    @NotEmpty(message = "Ingredient list must not be empty")
    Set<IngredientRequestDTO> ingredients,

    @Min(value=1, message="Servings must be at least 1")
    int servings,
    
    @NotNull(message = "Category can not be empty")
    Category category,

    @NotNull(message = "Difficulty can not be empty")
    Difficulty difficulty,

    boolean isPublic
){
}
