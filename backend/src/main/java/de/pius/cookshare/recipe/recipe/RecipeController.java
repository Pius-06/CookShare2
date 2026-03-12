package de.pius.cookshare.recipe.recipe;

import de.pius.cookshare.recipe.recipe.dto.RecipeResponseDTO;
import de.pius.cookshare.recipe.recipe.dto.RecipeSearchRequestDTO;
import de.pius.cookshare.security.CustomUserDetails;

import lombok.AllArgsConstructor;

import java.util.Set;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/me")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<Set<RecipeResponseDTO>> getOwnRecipes(@AuthenticationPrincipal CustomUserDetails user) {

        Set<RecipeResponseDTO> recipes = RecipeResponseDTO.from(
                recipeService.getOwnRecipes(user.getUser().getId()));
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/{id}")
    @PreAuthorize("#id == auhthentication.principal.id")
    public ResponseEntity<Set<RecipeResponseDTO>> getRecipesByUserId(@PathVariable("id") Long id) {

        Set<RecipeResponseDTO> recipes = RecipeResponseDTO.from(recipeService.getRecipesByUserId(id));
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/")
    public ResponseEntity<Page<RecipeResponseDTO>> getAllRecipes(RecipeSearchRequestDTO dto,
            Pageable pageable) {

        return ResponseEntity.ok(recipeService.getAllRecipes(dto, pageable));
    }

    

    // eigenen rezepte
    // rezepte von anderen
    // rezepte von allen + filter
    // rezept CRUD
    // pagination
    // filter (queryparameter)
}
