package de.pius.cookshare.user;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.pius.cookshare.auth.dto.RegisterRequestDTO;
import de.pius.cookshare.user.dto.UserRequestDTO;
import de.pius.cookshare.user.dto.UserResponseDTO;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService; // ??? Dependency Injection

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Set<UserResponseDTO>> getAllUser() {

        Set<UserResponseDTO> users = UserResponseDTO.from(userService.getAllUser());
        return new ResponseEntity<Set<UserResponseDTO>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Long id) {
        
        UserResponseDTO user = UserResponseDTO.from(userService.getUser(id));
        return new ResponseEntity<UserResponseDTO>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody RegisterRequestDTO userData) {

        UserResponseDTO user = UserResponseDTO.from(userService.createUser(userData));
        return new ResponseEntity<UserResponseDTO>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserRequestDTO userData) {

        UserResponseDTO user = UserResponseDTO.from(userService.updateUser(id, userData));
        return new ResponseEntity<UserResponseDTO>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
