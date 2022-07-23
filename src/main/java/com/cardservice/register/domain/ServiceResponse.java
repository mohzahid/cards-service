package com.cardservice.register.domain;

import org.springframework.http.HttpHeaders;

public class ServiceResponse<T> {
    T response;
    HttpHeaders httpHeaders;

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
}
