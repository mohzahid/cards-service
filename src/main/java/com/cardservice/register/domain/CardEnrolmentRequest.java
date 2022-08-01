package com.cardservice.register.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CardEnrolmentRequest {

    @NotNull
    @Size(min = 16, max = 19, message = "Card Number should be 16 to 19 digits long")
    private String cardNumber;

    @NotNull
    @NotEmpty
    private String cardHolderName;
    private float cardLimit;

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public float getCardLimit() {
        return cardLimit;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setCardLimit(float cardLimit) {
        this.cardLimit = cardLimit;
    }

    public String getString() {
        {
            String str = this.getCardHolderName().concat(" ").concat(this.getCardNumber())
                    .concat(" ").concat(String.valueOf(this.getCardLimit()));
            return str;
        }
    }
}
