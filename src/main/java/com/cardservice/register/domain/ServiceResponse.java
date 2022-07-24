package com.cardservice.register.domain;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class ServiceResponse<T> {
    T response;
    HttpHeaders httpHeaders;

    HttpStatus httpStatus;

    public T getResponse()
    {
        return this.response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
