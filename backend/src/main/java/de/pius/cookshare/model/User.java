package de.pius.cookshare.model;

import java.time.LocalDateTime;
import java.util.HashSet;
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

@Entity
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
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_follows", joinColumns = @JoinColumn(name = "follower_id"), inverseJoinColumns = @JoinColumn(name = "followed_id"))
    private Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy = "following")
    private Set<User> following = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, // Operationen am Parent werden automatisch auf das Child angewendet
            orphanRemoval = true // Wenn die Beziehung entfernt wird, wird das Child gelöscht
    )
    private Image profilImage;

    @Transient
    private int countFollowers;

    @Transient
    private int countFollowing;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Recipe> ownRecipes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_recipe", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> likedRecipes = new HashSet<>();

    public User() {
    }

    public User(
            Long id,
            String username,
            String firstname,
            String lastname,
            String email,
            String password,
            String bio,
            LocalDateTime createdAt,
            LocalDateTime lastLoggin,
            boolean isActive,
            int failedLoginAttemps,
            Set<Role> roles,
            Set<User> followers,
            Set<User> following,
            Set<Recipe> ownRecipes,
            Set<Recipe> likedRecipes) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.createdAt = createdAt;
        this.lastLogin = lastLoggin;
        this.isActive = isActive;
        this.failedLoginAttemps = failedLoginAttemps;
        this.roles = roles;
        this.followers = followers;
        this.following = following;
        this.ownRecipes = ownRecipes;
        this.likedRecipes = likedRecipes;
    }

    @Override
    public String toString() {
        return "User [id=" + id +
                ", username=" + username +
                ", firstname=" + firstname +
                ", lastname=" + lastname +
                ", email=" + email +
                ", password=" + password +
                ", bio=" + bio +
                ", createdAt=" + createdAt +
                ", lastLoggin=" + lastLogin +
                ", isActive=" + isActive +
                ", failedLoginAttemps=" + failedLoginAttemps +
                ", roles=" + roles +
                ", followers=" + followers +
                ", following=" + following +
                ", countFollowers=" + countFollowers +
                ", countFollowing=" + countFollowing
                + "]";
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getFailedLoginAttemps() {
        return failedLoginAttemps;
    }

    public void setFailedLoginAttemps(int failedLoginAttemps) {
        this.failedLoginAttemps = failedLoginAttemps;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public Image getProfilImage() {
        return profilImage;
    }

    public void setProfilImage(Image profilImage) {
        this.profilImage = profilImage;
    }

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

    public Set<Recipe> getOwnRecipes() {
        return ownRecipes;
    }

    public void setOwnRecipes(Set<Recipe> ownRecipes) {
        this.ownRecipes = ownRecipes;
    }

    public Set<Recipe> getLikedRecipes() {
        return likedRecipes;
    }

    public void setLikedRecipes(Set<Recipe> likedRecipes) {
        this.likedRecipes = likedRecipes;
    }
}
