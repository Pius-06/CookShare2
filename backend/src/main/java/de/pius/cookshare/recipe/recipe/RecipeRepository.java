package de.pius.cookshare.recipe.recipe;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("""
            SELECT r
            FROM Recipe r
            WHERE r.authorId = :authorId
            AND (:title IS NULL OR LOWER(r.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND (:category IS NULL OR r.category = :category)
            AND (:difficulty IS NULL OR r.difficulty = :difficulty)
            """)
    Page<Recipe> findByAuthorIdAndFilter(
            @Param("authorId") Long authorId,
            @Param("title") String title,
            @Param("category") Category category,
            @Param("difficulty") Difficulty difficulty,
            Pageable pageable);

    @Query("""
            SELECT r
            FROM Recipe r
            WHERE r.authorId = :authorId
            AND r.isPublic = true
            AND (:title IS NULL OR LOWER(r.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND (:category IS NULL OR r.category = :category)
            AND (:difficulty IS NULL OR r.difficulty = :difficulty)
                        """)
    Page<Recipe> findPublicRecipesByAuthorIdAndFilter(
            @Param("authorId") Long authorId,
            @Param("title") String title,
            @Param("category") Category category,
            @Param("difficulty") Difficulty difficulty,
            Pageable pageable);

    @Query("""
            SELECT r
            FROM Recipe r
            WHERE (:title IS NULL OR LOWER(r.title) LIKE LOWER(CONCAT('%', :title, '%')))
            AND (:category IS NULL OR r.category = :category)
            AND (:difficulty IS NULL OR r.difficulty = :difficulty)
            """)
    Page<Recipe> findAllByFilter(
            @Param("title") String title,
            @Param("category") Category category,
            @Param("difficulty") Difficulty difficulty,
            Pageable pageable);

}
