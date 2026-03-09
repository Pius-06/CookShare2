package de.pius.cookshare.user;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import de.pius.cookshare.user.dto.UserRequestDTO;
import de.pius.cookshare.user.dto.UserResponseDTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService; // ??? Dependency Injection

    @GetMapping("")
    public ResponseEntity<Set<UserResponseDTO>> getAllUser() {

        Set<UserResponseDTO> users = UserResponseDTO.from(userService.getAllUser());
        return new ResponseEntity<Set<UserResponseDTO>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Long id) {

        UserResponseDTO user = UserResponseDTO.from(userService.getUser(id));
        return new ResponseEntity<UserResponseDTO>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequestDTO userData) {

        UserResponseDTO user = UserResponseDTO.from(userService.updateUser(id, userData));
        return new ResponseEntity<UserResponseDTO>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
