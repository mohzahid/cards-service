package com.cardservice.register.controller;

import com.cardservice.register.domain.CardEnrolmentRequest;
import com.cardservice.register.domain.CardEnrolmentResponse;
import com.cardservice.register.domain.CardRecords;
import com.cardservice.register.domain.ServiceResponse;
import com.cardservice.register.domain.enums.EnrolmentOutcome;
import com.cardservice.register.execute.CardRecordsApplication;
import com.cardservice.register.service.CardManagementService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CardRecordsApplication.class)
class CardRecordsControllerTest {

    @InjectMocks
    CardRecordsController cardRecordsController;

    @Mock
    CardManagementService cardManagementService;

    @Mock
    CardEnrolmentRequest cardEnrolmentRequest;

    @Test
    public void testCreateValidCardEnrolment_SuccessResponse() {

        ServiceResponse<CardEnrolmentResponse> serviceResponse = new ServiceResponse<>();

        serviceResponse.setHttpStatus(EnrolmentOutcome.PASSED.getHttpStatus());

        when(cardManagementService.addCard(cardEnrolmentRequest)).thenReturn(serviceResponse);

        ResponseEntity<CardEnrolmentResponse> responseEntity = cardRecordsController.addCard(cardEnrolmentRequest);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    public void testCreateInValidCardEnrolment_FailedResponse() {

        ServiceResponse<CardEnrolmentResponse> serviceResponse = new ServiceResponse<>();

        serviceResponse.setHttpStatus(EnrolmentOutcome.FAILED.getHttpStatus());

        when(cardManagementService.addCard(cardEnrolmentRequest)).thenReturn(serviceResponse);

        ResponseEntity<CardEnrolmentResponse> responseEntity = cardRecordsController.addCard(cardEnrolmentRequest);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void testGetAllCardDetails_SuccessResponse() {

        ServiceResponse<CardRecords> serviceResponse = new ServiceResponse<>();
        serviceResponse.setHttpStatus(HttpStatus.OK);

        when(cardManagementService.getAllCardRecords()).thenReturn(serviceResponse);

        ResponseEntity<CardRecords> responseEntity = cardRecordsController.getAllCards();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testGetAllCardDetails_FailedResponse() {

        ServiceResponse<CardRecords> serviceResponse = new ServiceResponse<>();
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        when(cardManagementService.getAllCardRecords()).thenReturn(serviceResponse);

        ResponseEntity<CardRecords> responseEntity = cardRecordsController.getAllCards();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
    }
}