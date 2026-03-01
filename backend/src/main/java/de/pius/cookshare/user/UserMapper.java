package de.pius.cookshare.user;

import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.user.dto.UserRequestDTO;

public class UserMapper {

    public static User toUser(UserRequestDTO dto) {
        return User.builder()
                .username(dto.username())
                .firstname(dto.firstname())
                .lastname(dto.lastname())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    public static Set<User> toUser(Set<UserRequestDTO> dtos) {
        return dtos.stream()
                .map(userDto -> toUser(userDto))
                .collect(Collectors.toSet());
    }
}
