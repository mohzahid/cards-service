package com.cardservice.register.dao;

import java.util.List;
public interface CardManagementDao {

    boolean addCard(CardDetails cardDetails);

    List<CardDetails> getAllCardRecord();
}
