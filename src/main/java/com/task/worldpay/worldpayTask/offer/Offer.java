package com.task.worldpay.worldpayTask.offer;

import java.util.Objects;

public class Offer implements AllOffer {
    private String offerName;
    private String offerDetailDescription;
    private String expiryDate;

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDetailDescription() {
        return offerDetailDescription;
    }

    public void setOfferDetailDescription(String offerDetailDescription) {
        this.offerDetailDescription = offerDetailDescription;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return getOfferName().equals(offer.getOfferName()) &&
                getOfferDetailDescription().equals(offer.getOfferDetailDescription()) &&
                getExpiryDate().equals(offer.getExpiryDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOfferName(), getOfferDetailDescription(), getExpiryDate());
    }
}
