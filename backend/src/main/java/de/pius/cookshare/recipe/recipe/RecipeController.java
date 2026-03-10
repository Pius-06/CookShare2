package de.pius.cookshare.recipe.recipe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.pius.cookshare.recipe.recipe.dto.RecipeResponseDTO;
import lombok.AllArgsConstructor;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/recipes")
public class RecipeController {
    
    private final RecipeService recipeService;

    @GetMapping()
    public String getAllRecipes() {
        return new String();
    }

    @GetMapping("/me/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<Set<RecipeResponseDTO>> getOwnRecipes(@PathVariable("id") Long id) {

        Set<RecipeResponseDTO> recipes= RecipeResponseDTO.from(recipeService.getOwnRecipes(id));
        return ResponseEntity.ok(recipes);
    }


    
}
