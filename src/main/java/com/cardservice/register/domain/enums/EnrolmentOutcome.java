package com.cardservice.register.domain.enums;

import org.springframework.http.HttpStatus;

public enum EnrolmentOutcome {
    PASSED("SUCCESS", "Card is Enrolled Successfully", HttpStatus.OK),
    FAILED("FAILED", "Card couldn't be added, try again", HttpStatus.BAD_REQUEST),
    DUPLICATE("FAILED", "Card already exists, try again", HttpStatus.CONFLICT);
    private final String status;
    private final String message;

    private final HttpStatus httpStatus;

    EnrolmentOutcome(String status, String message, HttpStatus httpStatus)
    {
        this.status = status;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
