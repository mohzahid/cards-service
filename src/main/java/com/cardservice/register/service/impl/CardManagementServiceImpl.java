package com.cardservice.register.service.impl;

import com.cardservice.register.constants.CardRecordsConstants;
import com.cardservice.register.dao.CardDetails;
import com.cardservice.register.dao.CardManagementRepository;
import com.cardservice.register.domain.CardEnrolmentRequest;
import com.cardservice.register.domain.CardEnrolmentResponse;
import com.cardservice.register.domain.CardRecords;
import com.cardservice.register.domain.ServiceResponse;
import com.cardservice.register.domain.enums.EnrolmentOutcome;
import com.cardservice.register.service.CardManagementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;



@Service
public class CardManagementServiceImpl implements CardManagementService {

    @Autowired
    CardManagementRepository cardManagementRepository;

    private static final String CLASSNAME = CardManagementServiceImpl.class.getName();
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private boolean isLuhn10CheckPassed(String cardNumber) {
        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat("execute - isLuhn10CheckPassed"));

        int length = cardNumber.length();
        int digit = 0;
        int luhn10Sum = 0;
        boolean isSecondDigit = false;

        LOGGER.info(CLASSNAME.concat("In Luhn10 check :")+length);
        if (length >= CardRecordsConstants.MIN_CARD_LENGTH && length <= CardRecordsConstants.MAX_CARD_LENGTH) {
            while (--length >= 0) {

                LOGGER.info(CLASSNAME.concat("In Luhn10 check :").concat(String.valueOf(cardNumber.charAt(length))));
                digit = cardNumber.charAt(length) - '0';
                if (digit >= 0 && digit <= 9) {
                    if (isSecondDigit) {
                        digit = digit * 2;
                    }

                    luhn10Sum += digit / 10;
                    luhn10Sum += digit % 10;

                    isSecondDigit = !isSecondDigit;
                } else
                    return false;
            }
            LOGGER.debug(CLASSNAME.concat("Inside Luhn10 check").concat(Integer.toString(luhn10Sum)));
            return (0 == luhn10Sum % 10);
        } else
            return false;
    }

    private boolean isCardDetailsValid(CardEnrolmentRequest cardEnrolmentRequest) {
        if (isLuhn10CheckPassed(cardEnrolmentRequest.getCardNumber())) {
            return (0 == cardEnrolmentRequest.getCardLimit());
        }
        return false;
    }

    @Override
    public ServiceResponse<CardEnrolmentResponse> addCard(CardEnrolmentRequest cardEnrolmentRequest) {

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat("execute - addCard"));

        CardDetails cardDetails = new CardDetails(cardEnrolmentRequest);
        CardEnrolmentResponse cardEnrolmentResponse = new CardEnrolmentResponse();
        ServiceResponse<CardEnrolmentResponse> serviceResponse = new ServiceResponse<>();

        if (isCardDetailsValid(cardEnrolmentRequest)) {
            cardManagementRepository.save(cardDetails);

            cardEnrolmentResponse.setResult(EnrolmentOutcome.PASSED.getStatus());
            cardEnrolmentResponse.setMessage(EnrolmentOutcome.PASSED.getMessage());
            serviceResponse.setHttpStatus(EnrolmentOutcome.PASSED.getHttpStatus());
            LOGGER.debug(CLASSNAME.concat(" In addCard -").concat(EnrolmentOutcome.PASSED.getStatus()));
        } else {
            cardEnrolmentResponse.setResult(EnrolmentOutcome.FAILED.getStatus());
            cardEnrolmentResponse.setMessage(EnrolmentOutcome.FAILED.getMessage());
            serviceResponse.setHttpStatus(EnrolmentOutcome.FAILED.getHttpStatus());

            LOGGER.debug(CLASSNAME.concat(" In addCard -").concat(EnrolmentOutcome.FAILED.getStatus()));
        }
        serviceResponse.setResponse(cardEnrolmentResponse);

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_EXIT).concat("exiting - addCard"));

        return serviceResponse;
    }

    @Override
    public ServiceResponse<CardRecords> getAllCardRecords() {
        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat("execute - addCard"));

        List<CardDetails> listCardDetails = new ArrayList<>();

        cardManagementRepository.findAll().forEach(listCardDetails::add);

        CardRecords cardRecords = new CardRecords(listCardDetails);

        ServiceResponse<CardRecords> serviceResponse = new ServiceResponse<>();
        serviceResponse.setResponse(cardRecords);
        serviceResponse.setHttpStatus(HttpStatus.OK);

        LOGGER.info(CLASSNAME.concat(com.cardservice.register.constants.CardRecordsConstants.METHOD_EXIT).concat("exiting - addCard"));
        return serviceResponse;
    }
}
