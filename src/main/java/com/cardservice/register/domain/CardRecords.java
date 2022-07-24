package com.cardservice.register.domain;

import com.cardservice.register.dao.CardDetails;

import java.util.List;

public class CardRecords {
    private List<CardDetails> listCards;

    public CardRecords(List<CardDetails> listCards) {
        this.listCards = listCards;
    }

    public List<CardDetails> getListCards() {
        return listCards;
    }

    public void setListCards(List<CardDetails> listCards) {
        this.listCards = listCards;
    }
}
