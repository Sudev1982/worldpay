package com.task.worldpay.worldpayTask.dao_service;

import com.task.worldpay.worldpayTask.model.persist.OfferDetails;
import com.task.worldpay.worldpayTask.model.response.AllOffers;
import com.task.worldpay.worldpayTask.offer.Offer;
import com.task.worldpay.worldpayTask.util.OfferUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OfferDaoMapper {

    public AllOffers createOffersResponse(List<OfferDetails> offerDetailsList) {
        AllOffers allOffers = null;
        if (offerDetailsList != null && offerDetailsList.size() > 0) {
            allOffers = new AllOffers();
            allOffers.setOfferDescription(Offer.offerDescription);
            allOffers.setCurrency(Offer.currency);
            List<Offer> offerList = new ArrayList<>();
            Offer offer = null;
            for (OfferDetails offerDetails : offerDetailsList) {
                offer = new Offer();
                offer.setOfferName(offerDetails.getId());
                offer.setOfferDetailDescription(offerDetails.getOfferDetails());
                offer.setExpiryDate(OfferUtils.formatDate(offerDetails.getValidity()));
                offerList.add(offer);
            }
            allOffers.setOfferModelList(offerList);
        }
        return allOffers;
    }

    public String createSuccessMessage(OfferDetails offerDetails) {
        if (offerDetails != null)
            return "Created/Updated Successfully";
        else
            return "Not Successfully";

    }

    public OfferDetails getOfferDetails(Offer offer) {
        OfferDetails offerDetails = new OfferDetails();
        try {
            offerDetails.setId(offer.getOfferName());
            offerDetails.setOfferDetails(Offer.offerDescription + " : " + offer.getOfferDetailDescription());
            offerDetails.setCurrency(Offer.currency);
            offerDetails.setValidity(LocalDateTime.parse(offer.getExpiryDate()));
        } catch (Exception e) {
            return null;
        }
        return offerDetails;
    }
}
