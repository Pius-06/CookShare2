package de.pius.cookshare.recipe.recipe;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;


    public Set<Recipe> getOwnRecipes(Long userId) {
        return recipeRepository.findByAuthorId(userId)
        .stream()
        .collect(Collectors.toSet());
    }
}
