package de.pius.cookshare.model;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")  // password wird ausgeschlossen
public class User {
    // TODO: Profileimage
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private String username;

    @Column(length = 200, nullable = false)
    private String firstname;

    @Column(length = 200, nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 200)
    private String bio;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime lastLogin;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private int failedLoginAttemps;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name = "user_follows", joinColumns = @JoinColumn(name = "follower_id"), inverseJoinColumns = @JoinColumn(name = "followed_id"))
    private Set<User> followers;

    @ManyToMany(mappedBy = "following")
    private Set<User> following;

    @OneToOne(cascade = CascadeType.ALL, // Operationen am Parent werden automatisch auf das Child angewendet
            orphanRemoval = true // Wenn die Beziehung entfernt wird, wird das Child gelöscht
    )
    private Image profilImage;

    @Transient
    private int countFollowers;

    @Transient
    private int countFollowing;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Recipe> ownRecipes;

    @ManyToMany
    @JoinTable(name = "user_recipe", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> likedRecipes;

    public int getCountFollowers() {
        if (followers == null)
            return 0;
        return followers.size();
    }

    public int getCountFollowing() {
        if (following == null)
            return 0;
        return following.size();
    }
}
