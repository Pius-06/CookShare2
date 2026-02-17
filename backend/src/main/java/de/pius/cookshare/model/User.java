package de.pius.cookshare.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import de.pius.cookshare.model.Role;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 20)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(min = 1, max = 200)
    @Pattern(regexp="[^0-9]")
    private String firstname;

    @NotBlank
    @Size(min = 1, max = 200)
    @Pattern(regexp="[^0-9]+$")
    private String lastname;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 200)
    @Pattern(regexp = """
            ((?=.*[A-Z]){1}
            (?=.*[0-9]){1}
            (?=.*[!@#$%^&*]){1}
            ).+$""")
    // ^: Präfix
    // $: Ende des Strings
    // ?=.*: Der String muss irgendwo das Pattern enthalten (?=PATTERN)
    // .* = beliebige Zeichen, 0 oder mehr
    private String password;

    @Size(max=500)
    private String bio;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotNull
    @UpdateTimestamp
    private LocalDateTime lastLoggin;

    @NotBlank
    private boolean isActive;

    @NotBlank
    private int failedLoginAttemps;

    @ManyToAny
    @JoinTable(
        name="user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
