package com.cardservice.register.service;

import com.cardservice.register.domain.CardRecords;
import com.cardservice.register.domain.CardEnrolmentRequest;
import com.cardservice.register.domain.CardEnrolmentResponse;
import com.cardservice.register.domain.ServiceResponse;

public interface CardManagementService {
    com.cardservice.register.domain.ServiceResponse<CardEnrolmentResponse> addCard(CardEnrolmentRequest cardEnrolmentRequest);
    ServiceResponse<CardRecords> getAllCardRecords();
}
