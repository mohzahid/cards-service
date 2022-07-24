package com.cardservice.register.domain;

import com.cardservice.register.dao.CardDetails;

import java.util.List;

public class CardRecords {

    private List<CardDetails> listCardDetails;

    public CardRecords(List<CardDetails> listCardDetails) {
        this.listCardDetails = listCardDetails;
    }

    public List<CardDetails> getListCardDetails() {
        return listCardDetails;
    }

    public void setListCardDetails(List<CardDetails> listCardDetails) {
        this.listCardDetails = listCardDetails;
    }
}
