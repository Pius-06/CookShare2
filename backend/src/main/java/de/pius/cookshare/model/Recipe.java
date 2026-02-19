package de.pius.cookshare.model;

import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import de.pius.cookshare.types.Category;
import de.pius.cookshare.types.Difficulty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 100)
    private String introduction;

    @Column(columnDefinition = "TEXT")
    private String preparation;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Image recipeImage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CookDuration duration;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private Set<Ingredient> ingridients;

    @Column(nullable = false)
    private int servings;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(mappedBy = "likedRecipes")
    private Set<User> likedBy = new HashSet<>();

    @Transient
    private int countLikes;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Recipe() {
    }

    public Recipe(
            Long id,
            String title,
            String introduction,
            String preparation,
            Image recipeImage,
            CookDuration duration,
            Set<Ingredient> ingridients,
            int servings,
            Category category,
            Difficulty difficulty,
            boolean isPublic,
            User author,
            Set<User> likedBy,
            LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.introduction = introduction;
        this.preparation = preparation;
        this.recipeImage = recipeImage;
        this.duration = duration;
        this.ingridients = ingridients;
        this.servings = servings;
        this.category = category;
        this.difficulty = difficulty;
        this.isPublic = isPublic;
        this.author = author;
        this.likedBy = likedBy;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public Image getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(Image recipeImage) {
        this.recipeImage = recipeImage;
    }

    public CookDuration getDuration() {
        return duration;
    }

    public void setDuration(CookDuration duration) {
        this.duration = duration;
    }

    public Set<Ingredient> getIngridients() {
        return ingridients;
    }

    public void setIngridients(Set<Ingredient> ingridients) {
        this.ingridients = ingridients;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Set<User> likedBy) {
        this.likedBy = likedBy;
    }

    public int getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(int countLikes) {
        this.countLikes = countLikes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
