package de.pius.cookshare.recipe.cookDuration;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString() 
@Embeddable 
public class CookDuration {
    
    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DurationUnit unit;
}
