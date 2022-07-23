package com.cardservice.register.service.impl;

import com.cardservice.register.domain.CardEnrolmentRequest;
import com.cardservice.register.domain.CardEnrolmentResponse;
import com.cardservice.register.domain.ServiceResponse;
import com.cardservice.register.service.CardManagementService;
import org.springframework.stereotype.Service;

@Service
public class CardManagementServiceImpl implements CardManagementService {

    private boolean isLuhn10CheckPassed(String cardNumber)
    {
        int length = cardNumber.length();
        int digit = 0;
        int luhn10Sum = 0;
        boolean isSecondDigit = false;
        if ( length >=16 && length <=19 )
        {
            while( length-- < 0 )
            {
                digit = cardNumber.charAt(length) - '0';
                if (digit >= 0 && digit <= 9)
                {
                    if( isSecondDigit )
                    {
                        digit = digit*2;
                    }

                    luhn10Sum += digit / 10;
                    luhn10Sum += digit % 10;

                    isSecondDigit = !isSecondDigit;
                }
                else
                    return false;
            }
            return (0 == luhn10Sum % 10);
        }
        else
            return false;
    }

    private boolean isCardDetailsValid(CardEnrolmentRequest cardEnrolmentRequest)
    {
        if(isLuhn10CheckPassed(cardEnrolmentRequest.getCardNumber()))
        {
            return (0 == cardEnrolmentRequest.getCardBalance());
        }
        return false;
    }

    @Override
    public ServiceResponse<CardEnrolmentResponse> addCard(CardEnrolmentRequest cardEnrolmentRequest)
    {
        if(isCardDetailsValid(cardEnrolmentRequest))
        {

        }
        return null;
    }
}
