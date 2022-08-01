package com.cardservice.register.common.exception;

import com.cardservice.register.common.constants.CardRecordsConstants;
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

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat(" execute - handleServiceException"));
        CardEnrolmentResponse cardEnrolmentResponse = new CardEnrolmentResponse();
        cardEnrolmentResponse.setResult(EnrolmentOutcome.DUPLICATE.getStatus());
        cardEnrolmentResponse.setMessage(EnrolmentOutcome.DUPLICATE.getMessage());

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_EXIT).concat(" exiting - handleServiceException"));
        return new ResponseEntity<>(cardEnrolmentResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e) {

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat(" execute - handleGenericException"));
        CardServiceException cardServiceException = new CardServiceException(e.getMessage());

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_EXIT).concat(" exiting - handleGenericException"));
        return new ResponseEntity<>(cardServiceException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
