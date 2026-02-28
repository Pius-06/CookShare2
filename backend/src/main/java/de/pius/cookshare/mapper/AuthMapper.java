package de.pius.cookshare.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.dto.auth.RegisterRequestDTO;
import de.pius.cookshare.model.user.User;

public class AuthMapper {
    public static User toUser(RegisterRequestDTO dto) {
        return User.builder()
                .username(dto.username())
                .firstname(dto.firstname())
                .lastname(dto.lastname())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    public static Set<User> toUser(Set<RegisterRequestDTO> dtos) {
        return dtos.stream()
                .map(userDto -> toUser(userDto))
                .collect(Collectors.toSet());
    }
}

