package com.task.worldpay.worldpayTask.dao_service;

import com.task.worldpay.worldpayTask.model.persist.OfferDetails;
import com.task.worldpay.worldpayTask.model.response.AllOffers;
import com.task.worldpay.worldpayTask.offer.Offer;
import com.task.worldpay.worldpayTask.repository.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferDaoService {


    @Autowired
    private OfferRepo offerRepo;

    @Autowired
    private OfferDaoMapper offerDaoHelper;

    public String createNewOffer(Offer offer) {
        OfferDetails offerDetails = offerRepo.save(offerDaoHelper.getOfferDetails(offer));
        return offerDaoHelper.createSuccessMessage(offerDetails);
    }

    public AllOffers getAllOffers() {
        return offerDaoHelper.createOffersResponse(offerRepo.findAllActiveOffers(LocalDateTime.now()));
    }

    public void deleteOffer(String id) {
        offerRepo.deleteById(id);
    }

    public AllOffers findOffer(String id) {
        List<OfferDetails> offerDetailsList = new ArrayList<>();
        Optional<OfferDetails> offerDetails = offerRepo.findById(id);
        if (offerDetails.isPresent()) {
            offerDetailsList.add(offerDetails.get());
        }
        return offerDaoHelper.createOffersResponse(offerDetailsList);
    }
}
