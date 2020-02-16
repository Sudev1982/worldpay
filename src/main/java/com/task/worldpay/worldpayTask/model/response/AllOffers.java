package com.task.worldpay.worldpayTask.model.response;

import com.task.worldpay.worldpayTask.offer.Offer;

import java.util.List;

public class AllOffers {
    private String currency;
    private String OfferDescription;
    private List<Offer> offerModelList;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOfferDescription() {
        return OfferDescription;
    }

    public void setOfferDescription(String offerDescription) {
        OfferDescription = offerDescription;
    }

    public List<Offer> getOfferModelList() {
        return offerModelList;
    }

    public void setOfferModelList(List<Offer> offerModelList) {
        this.offerModelList = offerModelList;
    }
}
