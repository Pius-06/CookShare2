package de.pius.cookshare.model;

import de.pius.cookshare.types.DurationUnit;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable // Klasse bekommt keine eigene Tabelle sondern, sondern ihre Felder direkt in der Tabelle der verwendenden 
// Entität gespeichert werden.
public class CookDuration {
    
    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DurationUnit unit;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public DurationUnit getUnit() {
        return unit;
    }

    public void setUnit(DurationUnit unit) {
        this.unit = unit;
    }

    public CookDuration(int amount, DurationUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public CookDuration() {
    }
}
