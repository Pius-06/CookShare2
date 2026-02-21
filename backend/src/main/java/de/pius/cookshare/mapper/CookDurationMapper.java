package de.pius.cookshare.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.DTO.recipeDTO.cookDurationDTO.CookDurationRequestDTO;
import de.pius.cookshare.model.CookDuration;

public class CookDurationMapper {

    public static CookDuration toCookDuration(CookDurationRequestDTO dto) {
        return CookDuration.builder()
                .amount(dto.amount())
                .unit(dto.unit())
                .build();
    }

    public static Set<CookDuration> toCookDuration(Set<CookDurationRequestDTO> dtos) {
        return dtos.stream()
                .map(recipeDto -> toCookDuration(recipeDto))
                .collect(Collectors.toSet());
    }
}
