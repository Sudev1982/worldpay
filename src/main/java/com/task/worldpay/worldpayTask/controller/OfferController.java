package com.task.worldpay.worldpayTask.controller;

import com.task.worldpay.worldpayTask.constant.ReqMapperConstant;
import com.task.worldpay.worldpayTask.model.response.AllOffers;
import com.task.worldpay.worldpayTask.offer.Offer;
import com.task.worldpay.worldpayTask.service.OfferManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oapi")
public class OfferController {

    @Autowired
    private OfferManagerService offerManagerService;


    @PostMapping(ReqMapperConstant.CREATE_OFFER)
    public String createOffer(@RequestBody Offer offer) {
        return offerManagerService.createNewOffer(offer);
    }

    @GetMapping(ReqMapperConstant.ALL_ACTIVE_OFFERS)
    public AllOffers getAllActiveOffers() {
        return offerManagerService.getAllActiveOffers();
    }

    @DeleteMapping(ReqMapperConstant.CANCEL_OFFER)
    public void cancelOffer(@PathVariable String offerId) {
        if (offerId != null && !offerId.isEmpty())
            offerManagerService.cancelOffer(offerId);
    }

    //we could expose a service to find all expired offers when needed
    //we could expose a service to find individual offer when needed

}
