package com.cardservice.register.common.exception;

import com.cardservice.register.domain.CardEnrolmentResponse;
import com.cardservice.register.domain.enums.EnrolmentOutcome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.invoke.MethodHandles;

@ControllerAdvice
public class CardServiceExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CLASSNAME = CardServiceExceptionHandler.class.getName();
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    @ExceptionHandler(value = CardServiceException.class)
    public ResponseEntity<Object> handleServiceException(CardServiceException cardServiceException) {

        CardEnrolmentResponse cardEnrolmentResponse = new CardEnrolmentResponse();
        cardEnrolmentResponse.setResult(EnrolmentOutcome.DUPLICATE.getStatus());
        cardEnrolmentResponse.setMessage(EnrolmentOutcome.DUPLICATE.getMessage());

        return new ResponseEntity<>(cardEnrolmentResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e) {

        CardServiceException cardServiceException = new CardServiceException(e.getMessage());

        return new ResponseEntity<>(cardServiceException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
