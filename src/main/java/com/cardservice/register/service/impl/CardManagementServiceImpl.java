package com.cardservice.register.service.impl;

import com.cardservice.register.domain.CardEnrolmentRequest;
import com.cardservice.register.domain.CardEnrolmentResponse;
import com.cardservice.register.domain.ServiceResponse;
import com.cardservice.register.service.CardManagementService;

public class CardManagementServiceImpl implements CardManagementService {

    private boolean isLuhn10CheckPassed(String cardNumber)
    {
        int length = cardNumber.length();
        if ( length >=16 && length <=19 )
        {
            
        }
        else
            return false;
    }

    private boolean isCardDetailsValid(CardEnrolmentRequest cardEnrolmentRequest)
    {


        return true;
    }

    @Override
    public ServiceResponse<CardEnrolmentResponse> addCard(CardEnrolmentRequest cardEnrolmentRequest)
    {
        if(isCardDetailsValid(cardEnrolmentRequest))
        {

        }
    }
}
