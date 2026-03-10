package de.pius.cookshare.recipe.recipe;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
    Set<Recipe> findByAuthorId(Long userId);
}
