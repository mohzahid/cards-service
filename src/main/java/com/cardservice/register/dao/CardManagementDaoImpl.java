package com.cardservice.register.dao;

import com.cardservice.register.common.constants.CardRecordsConstants;
import com.cardservice.register.common.exception.CardServiceException;
import com.cardservice.register.domain.enums.EnrolmentOutcome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CardManagementDaoImpl implements CardManagementDao {

    @Autowired
    private CardManagementRepository cardManagementRepository;

    public static final String CLASSNAME = CardManagementDaoImpl.class.getName();
    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public boolean addCard(CardDetails cardDetails) {

        boolean bRet = false;

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat(" execute - addCard "));

        CardDetails cardDetailsAlreadyExist = cardManagementRepository.findById(cardDetails.getCardNumber())
                .orElse(null);
        if (null == cardDetailsAlreadyExist) {
            cardManagementRepository.save(cardDetails);
            bRet = true;
        }
        else {
            bRet = false;
        }

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_EXIT).concat(" exiting - addCard ").concat(String.valueOf(bRet)));

        return bRet;

    }

    @Override
    public List<CardDetails> getAllCardRecord() {

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_ENTRY).concat(" execute - getAllCardRecord "));

        List<CardDetails> listCardDetails = new ArrayList<>();

        cardManagementRepository.findAll().forEach(listCardDetails::add);

        LOGGER.info(CLASSNAME.concat(CardRecordsConstants.METHOD_EXIT).concat(" exiting - getAllCardRecord "));

        return listCardDetails;
    }
}
