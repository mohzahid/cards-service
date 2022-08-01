package com.cardservice.register.controller;

import com.cardservice.register.common.constants.CardRecordsConstants;
import com.cardservice.register.domain.CardEnrolmentRequest;
import com.cardservice.register.domain.CardEnrolmentResponse;
import com.cardservice.register.domain.CardRecords;
import com.cardservice.register.domain.ServiceResponse;
import com.cardservice.register.service.CardManagementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.lang.invoke.MethodHandles;

@Validated
@RestController
@RequestMapping("/cards")
public class CardRecordsController  {

    @Autowired
    private CardManagementService cardManagementService;
    private static final String CLASSNAME = CardRecordsController.class.getName();
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping(value = "/ping", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> pingCardRecordApplication()
    {
        LOGGER.info("Ping method invoked");
        return new ResponseEntity<String>("Ping Success", HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value="/enrol", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CardEnrolmentResponse> addCard(@Validated @RequestBody CardEnrolmentRequest cardEnrolmentRequest) throws Exception
    {
        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat(" execute - addCard"));

        ServiceResponse<CardEnrolmentResponse> serviceResponse = cardManagementService.addCard(cardEnrolmentRequest);

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_EXIT).concat(" exiting - addCard"));

        return new ResponseEntity<CardEnrolmentResponse>(serviceResponse.getResponse(), serviceResponse.getHttpHeaders(), serviceResponse.getHttpStatus() );
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="getAll", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CardRecords> getAllCards() throws Exception
    {
        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat(" execute - getAllCards: "));

        ServiceResponse<CardRecords> serviceResponse = cardManagementService.getAllCardRecords();

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_EXIT).concat(" exiting - getAllCards: "));
        return new ResponseEntity<CardRecords>(serviceResponse.getResponse(), serviceResponse.getHttpHeaders(), serviceResponse.getHttpStatus());
    }
}
