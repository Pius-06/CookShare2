package de.pius.cookshare.model;

import de.pius.cookshare.types.DurationUnit;

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
@Embeddable // Klasse bekommt keine eigene Tabelle sondern, sondern ihre Felder direkt in der Tabelle der verwendenden 
// Entität gespeichert werden.
public class CookDuration {
    
    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DurationUnit unit;

    public static Object stream() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stream'");
    }
}
