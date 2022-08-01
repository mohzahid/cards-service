package com.cardservice.register.common.utility;

import com.cardservice.register.common.constants.CardRecordsConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class Util {
    public static final String CLASSNAME = Util.class.getName();
    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * This methos is used to validate card number based upon Luhn10 algo
     * @param cardNumber
     * @return : True if card number is valid otherwise false
     */
    public static boolean isLuhn10CheckPassed(String cardNumber) {
        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat("execute - isLuhn10CheckPassed"));

        int digit = 0;
        int luhn10Sum = 0;
        boolean isSecondDigit = false;
        boolean bRet = false;

        cardNumber = cardNumber.replaceAll("\\s+","");
        int length = cardNumber.length();

        LOGGER.info(CLASSNAME.concat("card number is-").concat(cardNumber));
        if (length >= CardRecordsConstants.MIN_CARD_LENGTH && length <= CardRecordsConstants.MAX_CARD_LENGTH) {
            while (--length >= 0) {

                LOGGER.debug(CLASSNAME.concat("In Luhn10 check :").concat(String.valueOf(cardNumber.charAt(length))));
                digit = cardNumber.charAt(length) - '0';
                if (digit >= 0 && digit <= 9) {
                    if (isSecondDigit) {
                        digit = digit * 2;
                    }

                    luhn10Sum += digit / 10;
                    luhn10Sum += digit % 10;

                    isSecondDigit = !isSecondDigit;
                } else {
                    bRet = false;
                    break;
                }
            }
            LOGGER.debug(CLASSNAME.concat("Inside Luhn10 check").concat(Integer.toString(luhn10Sum)));
            bRet = ((0 == luhn10Sum % 10) && (0 != luhn10Sum / 10));
        } else
            bRet = false;

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_EXIT).concat(" exiting - isLuhn10CheckPassed : ").concat(String.valueOf(bRet)));

        return bRet;
    }
}
