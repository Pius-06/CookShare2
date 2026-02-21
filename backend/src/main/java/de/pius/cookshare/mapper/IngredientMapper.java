package de.pius.cookshare.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import de.pius.cookshare.DTO.recipeDTO.ingredientDTO.IngredientRequestDTO;
import de.pius.cookshare.model.Ingredient;

@Component
public class IngredientMapper {

    public static Ingredient toIngredient(IngredientRequestDTO dto) {
        return Ingredient.builder()
                .amount(dto.amount())
                .unit(dto.unit())
                .name(dto.name())
                .build();
    }

    public static Set<Ingredient> toIngredient(Set<IngredientRequestDTO> dtos) {
        return dtos.stream()
                .map(ingredientDto -> toIngredient(ingredientDto))
                .collect(Collectors.toSet());
    }
}
