package com.cardservice.register.service;

import com.cardservice.register.domain.CardEnrolmentRequest;
import com.cardservice.register.domain.CardEnrolmentResponse;
import com.cardservice.register.domain.ServiceResponse;

public interface CardManagementService {

    ServiceResponse<CardEnrolmentResponse> addCard(CardEnrolmentRequest cardEnrolmentRequest);
}
