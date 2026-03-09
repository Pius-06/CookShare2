package de.pius.cookshare.user;

import java.util.Set;
import java.util.stream.Collectors;

import de.pius.cookshare.user.dto.UserRequestDTO;
import de.pius.cookshare.user.dto.UserUpdateDTO;

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

    public static User updateUser(UserUpdateDTO dto, User user) {
        if (dto == null || user == null)
            return user;

        if (dto.firstname() != null)
            user.setFirstname(dto.firstname());

        if (dto.lastname() != null)
            user.setLastname(dto.lastname());

        if (dto.username() != null)
            user.setUsername(dto.username());

        if (dto.email() != null)
            user.setEmail(dto.email());

        if (dto.bio() != null)
            user.setBio(dto.bio());

        return user;
    }
}
