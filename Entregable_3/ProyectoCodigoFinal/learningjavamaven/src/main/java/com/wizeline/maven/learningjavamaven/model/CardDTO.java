package com.wizeline.maven.learningjavamaven.model;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class CardDTO {

    private long cardNumber;
    private LocalDateTime expirationDate;
    private String cardIssuer;
    private String cardVerificationValue;

    private String cardType;

    public CardDTO() {
    }

    public CardDTO(CardDTO target) {
        if(target!= null){
            this.cardType = target.cardType;
            this.cardIssuer = target.cardIssuer;
            this.cardVerificationValue = target.cardVerificationValue;
            this.cardNumber = target.cardNumber;
            this.expirationDate = target.expirationDate;
        }
    }

    public abstract CardDTO clone();

    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof CardDTO)) return false;
        CardDTO cardDTO2 = (CardDTO) object2;
        return cardDTO2.cardNumber == cardNumber && Objects.equals(cardDTO2.cardType , cardType)   &&
                Objects.equals(cardDTO2.cardVerificationValue,cardVerificationValue) &&
                Objects.equals(cardDTO2.cardIssuer , cardIssuer) && cardDTO2.expirationDate == expirationDate;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(String cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public String getCardVerificationValue() {
        return cardVerificationValue;
    }

    public void setCardVerificationValue(String cardVerificationValue) {
        this.cardVerificationValue = cardVerificationValue;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
