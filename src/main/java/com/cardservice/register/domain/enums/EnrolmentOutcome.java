package com.cardservice.register.domain.enums;

public enum EnrolmentOutcome {
    PASSED("SUCCESS", "Card is Enrolled Successfully"),
    FAILED("FAILED", "Card couldn't be added, try again");

    private final String status;
    private final String message;

    EnrolmentOutcome(String status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
