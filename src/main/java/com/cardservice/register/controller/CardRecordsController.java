package com.cardservice.register.controller;

import com.cardservice.register.constants.CardRecordsConstants;
import com.cardservice.register.domain.CardEnrolmentRequest;
import com.cardservice.register.domain.CardEnrolmentResponse;
import com.cardservice.register.domain.ServiceResponse;
import com.cardservice.register.service.CardManagementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/cards")
public class CardRecordsController  {

    @Autowired
    public CardManagementService cardManagementService;
    private static final String CLASSNAME = CardRecordsController.class.getName();
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    @GetMapping(value = "/ping", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> pingCardRecordApplication()
    {
        LOGGER.info("Ping method invoked");
        return new ResponseEntity<String>("Ping Success", HttpStatus.OK);
    }
    public ResponseEntity<CardEnrolmentResponse> addCard(@RequestBody CardEnrolmentRequest cardEnrolmentRequest)
    {
        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat(" execute - addCard"));

        ServiceResponse<CardEnrolmentResponse> serviceResponse = cardManagementService.addCard(cardEnrolmentRequest);

        return new ResponseEntity<CardEnrolmentResponse>(serviceResponse.getResponse(), serviceResponse.getHttpHeaders(), HttpStatus.OK);
    }
}
