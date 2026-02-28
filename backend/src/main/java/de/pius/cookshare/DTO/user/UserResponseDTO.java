package de.pius.cookshare.dto.user;

import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.dto.recipe.RecipeResponseDTO;
import de.pius.cookshare.model.user.User;
import lombok.Builder;

@Builder
public record UserResponseDTO(
        Long id,

        String username,

        String firstname,

        String lastname,

        String email,

        String bio,

        boolean isActive,

        int countFollowers,

        int countFollowing,

        Set<RecipeResponseDTO> ownRecipes,

        Set<RecipeResponseDTO> likedRecipes
) {
    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getBio(),
                user.isActive(),
                user.getCountFollowers(),
                user.getCountFollowing(),
                RecipeResponseDTO.from(user.getOwnRecipes()),
                RecipeResponseDTO.from(user.getLikedRecipes()));
    }

    public static Set<UserResponseDTO> from(Set<User> users) {
        return users.stream()
                .map(user -> from(user))
                .collect(Collectors.toSet());
    }
}
