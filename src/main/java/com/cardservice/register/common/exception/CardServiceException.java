package com.cardservice.register.common.exception;

public class CardServiceException extends RuntimeException {

    private final String result = "FAILED";
    private String message;

    public CardServiceException(String ex) {
        super(ex);
        this.message = ex;
    }
}
