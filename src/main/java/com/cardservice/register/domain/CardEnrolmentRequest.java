package com.cardservice.register.domain;

import org.springframework.lang.NonNull;

public class CardEnrolmentRequest {
   /* @NonNull
    @Size(min=16, max=19, message = "Card Number should be 16 to 19 digits long")*/
    private String cardNumber;
    private String cardHolderName;
    private float cardBalance;

    public String getCardNumber() {
        return cardNumber;
    }

    public float getCardBalance() {
        return cardBalance;
    }
}
