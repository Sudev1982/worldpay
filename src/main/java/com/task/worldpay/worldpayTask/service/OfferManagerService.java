package com.task.worldpay.worldpayTask.service;

import com.task.worldpay.worldpayTask.dao_service.OfferDaoService;
import com.task.worldpay.worldpayTask.model.response.AllOffers;
import com.task.worldpay.worldpayTask.offer.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferManagerService {

    @Autowired
    private OfferDaoService offerDaoService;

    public String createNewOffer(Offer offer) {
        return offerDaoService.createNewOffer(offer);
    }

    public AllOffers getAllActiveOffers() {
        return offerDaoService.getAllOffers();

    }

    public void cancelOffer(String id) {
        offerDaoService.deleteOffer(id);

    }

    public AllOffers findOffer(String id) {
        return offerDaoService.findOffer(id);
    }

}
