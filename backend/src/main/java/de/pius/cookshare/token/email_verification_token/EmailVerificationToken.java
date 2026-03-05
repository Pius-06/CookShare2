package de.pius.cookshare.token.email_verification_token;

import de.pius.cookshare.token.Token;
import de.pius.cookshare.token.email_verification_token.exception.EmailVerificationTokenAlreadyUsed;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmailVerificationToken extends Token {
    @Column(nullable = false)
    private boolean used;

    public void markAsUsed() {
        this.used = true;
    }

    public void validate() {
        super.validate();
        if (isUsed())
            throw new EmailVerificationTokenAlreadyUsed();
    }
}
