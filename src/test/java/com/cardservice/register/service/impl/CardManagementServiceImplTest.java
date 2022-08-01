package com.cardservice.register.service.impl;

import com.cardservice.register.common.exception.CardServiceException;
import com.cardservice.register.dao.CardDetails;
import com.cardservice.register.dao.CardManagementRepository;
import com.cardservice.register.domain.CardEnrolmentRequest;
import com.cardservice.register.domain.CardEnrolmentResponse;
import com.cardservice.register.domain.CardRecords;
import com.cardservice.register.domain.ServiceResponse;
import com.cardservice.register.domain.enums.EnrolmentOutcome;
import com.cardservice.register.execute.CardRecordsApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CardRecordsApplication.class)
class CardManagementServiceImplTest {
    @InjectMocks
    CardManagementServiceImpl cardManagementService;
    @Mock
    CardManagementRepository cardManagementRepository;
    @Mock
    CardDetails cardDetails;
    @Test
    public void testAddCard_SuccessResponse() throws CardServiceException {

        CardEnrolmentRequest cardEnrolmentRequest = getCardDetails("4546384820443365");

        when(cardManagementRepository.findById(cardDetails.getCardNumber())).thenReturn(null);
        when(cardManagementRepository.save(cardDetails)).thenReturn(null);

        ServiceResponse<CardEnrolmentResponse> serviceResponse = cardManagementService.addCard(cardEnrolmentRequest);

        assertThat(serviceResponse.getResponse().getResult().equalsIgnoreCase(EnrolmentOutcome.PASSED.getStatus()));
    }
    @Test
    public void testAddCard_FailedResponse() throws CardServiceException {
        CardEnrolmentRequest cardEnrolmentRequest = getCardDetails("4546384820443367");

        when(cardManagementRepository.save(cardDetails)).thenReturn(null);

        ServiceResponse<CardEnrolmentResponse> serviceResponse = cardManagementService.addCard(cardEnrolmentRequest);

        assertThat(serviceResponse.getResponse().getResult().equalsIgnoreCase(EnrolmentOutcome.FAILED.getStatus()));
    }
    @Test
    public void testGetAllCardDetails_SuccessResponse() {

        List<CardDetails> listCardDetails = new ArrayList<>();
        List<Object> objects = mock(List.class);

        when(cardManagementRepository.findAll()).thenReturn(listCardDetails);

        when(objects.size()).thenReturn(1);

        ServiceResponse<CardRecords> serviceResponse = cardManagementService.getAllCardRecords();

        assertThat(serviceResponse.getHttpStatus().toString().equalsIgnoreCase("200"));
    }
    @Test
    public void testGetAllCardDetails_NoContentResponse() {

        List<CardDetails> listCardDetails = new ArrayList<>();
        List<Object> objects = mock(List.class);

        when(cardManagementRepository.findAll()).thenReturn(listCardDetails);

        when(objects.size()).thenReturn(0);

        ServiceResponse<CardRecords> serviceResponse = cardManagementService.getAllCardRecords();

        assertThat(serviceResponse.getHttpStatus().toString().equalsIgnoreCase("204"));
    }
    private CardEnrolmentRequest getCardDetails(String cardNumber) {
        CardEnrolmentRequest cardEnrolmentRequest = new CardEnrolmentRequest();
        cardEnrolmentRequest.setCardNumber(cardNumber);
        cardEnrolmentRequest.setCardHolderName("Test User");
        cardEnrolmentRequest.setCardLimit(0);

        return cardEnrolmentRequest;
    }

}