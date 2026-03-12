package de.pius.cookshare.recipe.recipe;

import java.util.Set;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Set<Recipe> findByAuthorId(Long authorId);

    @Query("""
            SELECT r
            FROM Recipe r
            WHERE r.authorId = ?1
            AND r.isPublic = true
            """)
    Set<Recipe> findPublicRecipesByAuthorId(Long authorId);

    Page<Recipe> findAll(Pageable pageable);
}
