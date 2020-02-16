package com.task.worldpay.worldpayTask.model.persist;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class OfferDetails {
    @Id
    private String id;
    private String offerDetails;
    private String currency;
    private LocalDateTime validity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(String offerDetails) {
        this.offerDetails = offerDetails;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getValidity() {
        return validity;
    }

    public void setValidity(LocalDateTime validity) {
        this.validity = validity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfferDetails)) return false;
        OfferDetails that = (OfferDetails) o;
        return getId().equals(that.getId()) &&
                getOfferDetails().equals(that.getOfferDetails()) &&
                getCurrency().equals(that.getCurrency()) &&
                getValidity().equals(that.getValidity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOfferDetails(), getCurrency(), getValidity());
    }
}
