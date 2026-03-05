package de.pius.cookshare.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import de.pius.cookshare.image.Image;
import de.pius.cookshare.recipe.recipe.Recipe;
import de.pius.cookshare.token.email_verification_token.EmailVerificationToken;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = { "password", "verificationToken", "following", "followers", "ownRecipes", "likedRecipes" })
public class User implements UserDetails {
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
    @Builder.Default
    private boolean isActive = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToMany
    @JoinTable(name = "user_follows", joinColumns = @JoinColumn(name = "follower_id"), inverseJoinColumns = @JoinColumn(name = "followed_id"))
    private Set<User> following;

    @ManyToMany(mappedBy = "following")
    private Set<User> followers;

    @OneToOne(cascade = CascadeType.ALL, // Operationen am Parent werden automatisch auf das Child angewendet
            orphanRemoval = true // Wenn die Beziehung entfernt wird, wird das Child gelöscht
    )
    private Image profilImage;

    private int countFollowers;

    private int countFollowing;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Recipe> ownRecipes;

    @ManyToMany
    @JoinTable(name = "user_recipe", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> likedRecipes;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmailVerificationToken verificationToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email; 
    }
}
