package com.cardservice.register.service.impl;

import com.cardservice.register.common.constants.CardRecordsConstants;
import com.cardservice.register.common.exception.CardServiceException;
import com.cardservice.register.common.utility.Util;
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

    /**
     * Method to validate card details in Request.
     * @param cardEnrolmentRequest
     * @return
     */
    private boolean isCardDetailsValid(CardEnrolmentRequest cardEnrolmentRequest) {
        if (Util.isLuhn10CheckPassed(cardEnrolmentRequest.getCardNumber())) {
            return (0 == cardEnrolmentRequest.getCardLimit());
        }
        return false;
    }

    /**
     * Service layer to add the new card
     * @param cardEnrolmentRequest
     * @return : SUCCESS if card is added successfully
     *           FAILED if card already exists
     */
    @Override
    public ServiceResponse<CardEnrolmentResponse> addCard(CardEnrolmentRequest cardEnrolmentRequest) {

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat("execute - addCard"));

        CardDetails cardDetails = new CardDetails(cardEnrolmentRequest);
        CardEnrolmentResponse cardEnrolmentResponse = new CardEnrolmentResponse();
        ServiceResponse<CardEnrolmentResponse> serviceResponse = new ServiceResponse<>();

        if (isCardDetailsValid(cardEnrolmentRequest)) {

            CardDetails cardDetailsAlreadyExist = cardManagementRepository.findById(cardDetails.getCardNumber())
                            .orElse(null);
            if (null == cardDetailsAlreadyExist) {
                cardManagementRepository.save(cardDetails);
            }
            else {
                throw new CardServiceException(EnrolmentOutcome.DUPLICATE.getMessage());
            }

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

    /**
     * This method is used to retrieve all enrolled card from the DB.
     * @return : list of cards if atleast one card is found in DB.
     *           empty list and response code 204 if not record in DB.
     */
    @Override
    public ServiceResponse<CardRecords> getAllCardRecords() {
        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat("execute - addCard"));

        ServiceResponse<CardRecords> serviceResponse = new ServiceResponse<>();

        List<CardDetails> listCardDetails = new ArrayList<>();

        cardManagementRepository.findAll().forEach(listCardDetails::add);

        CardRecords cardRecords = new CardRecords(listCardDetails);

            serviceResponse.setResponse(cardRecords);
        if( 0 != listCardDetails.size() ) {
            serviceResponse.setHttpStatus(HttpStatus.OK);
        }
        else {
            serviceResponse.setHttpStatus(HttpStatus.NO_CONTENT);
        }
        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_EXIT).concat("exiting - addCard"));
        return serviceResponse;
    }
}
