package com.cardservice.register.dao;

import com.cardservice.register.domain.CardEnrolmentRequest;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardDetails {
    @Id
    private String cardNumber;
    private String cardHolderName;
    private float cardLimit;
    private float cardBalance;

    public CardDetails( ) {

    }


    public CardDetails(String cardNumber, String cardHolderName, float cardLimit, float cardBalance) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardLimit = cardLimit;
        this.cardBalance = cardBalance;
    }

    public CardDetails(CardEnrolmentRequest cardEnrolmentRequest) {
        this.cardNumber = cardEnrolmentRequest.getCardNumber();
        this.cardHolderName = cardEnrolmentRequest.getCardHolderName();
        this.cardLimit = cardEnrolmentRequest.getCardLimit();
        this.cardBalance = 0;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public float getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(float cardLimit) {
        this.cardLimit = cardLimit;
    }

    public float getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(float cardBalance) {
        this.cardBalance = cardBalance;
    }
}
