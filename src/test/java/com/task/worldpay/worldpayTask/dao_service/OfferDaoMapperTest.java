package com.task.worldpay.worldpayTask.dao_service;

import com.task.worldpay.worldpayTask.model.persist.OfferDetails;
import com.task.worldpay.worldpayTask.model.response.AllOffers;
import com.task.worldpay.worldpayTask.offer.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class OfferDaoMapperTest {

    @Autowired
    private OfferDaoMapper offerDaoMapper;

    private List<OfferDetails> offerDetailsList;
    private AllOffers allOffers;
    private Offer offer1;

    @BeforeEach
    public void setUp() {
        offerDetailsList = new ArrayList<>();
        OfferDetails offerDetails = null;
        for (int i = 0; i < 2; i++) {
            offerDetails = new OfferDetails();
            offerDetails.setId("offer" + i);
            offerDetails.setCurrency("GBP");
            offerDetails.setValidity(LocalDateTime.parse("2021-02-27T15:39:46.058"));
            offerDetails.setOfferDetails("Sale On : When you buy for 30 pounds get 2 pounds off");
            offerDetailsList.add(offerDetails);
        }

        allOffers = new AllOffers();
        allOffers.setCurrency("GBP");
        allOffers.setOfferDescription("Sale On");
        List<Offer> offerList = new ArrayList<>();
        Offer offer = null;
        for (int j = 0; j < 2; j++) {
            offer = new Offer();
            offer.setOfferName("offer" + j);
            offer.setOfferDetailDescription("Sale On : When you buy for 30 pounds get 2 pounds off");
            offer.setExpiryDate("27 February 2021 15:39:46");
            offerList.add(offer);
        }
        allOffers.setOfferModelList(offerList);

        offer1 = new Offer();
        offer1.setOfferName("offer0");
        offer1.setOfferDetailDescription("When you buy for 30 pounds get 2 pounds off");
        offer1.setExpiryDate("2021-02-27T15:39:46.058");
    }


    @Test
    @DisplayName("createOffersResponseTesting")
    public void createOffersResponseTest() {
        AllOffers all = offerDaoMapper.createOffersResponse(offerDetailsList);
        assertNotNull(all);
        assertEquals(allOffers.getCurrency(), all.getCurrency());
        assertEquals(allOffers.getOfferDescription(), all.getOfferDescription());
        assertArrayEquals(allOffers.getOfferModelList().toArray(), all.getOfferModelList().toArray());
    }

    @Test
    @DisplayName("createSuccessMessageTesting")
    public void createSuccessMessageTest() {
        assertNotNull(offerDaoMapper.createSuccessMessage(offerDetailsList.get(0)));
        assertEquals("Created/Updated Successfully", offerDaoMapper.createSuccessMessage(offerDetailsList.get(0)));
        assertEquals("Not Successfully", offerDaoMapper.createSuccessMessage(null));
    }

    @Test
    @DisplayName("getOfferDetailsTesting")
    public void getOfferDetailsTest() {
        assertEquals(offerDetailsList.get(0), offerDaoMapper.getOfferDetails(offer1));
        assertEquals(offerDetailsList.get(0).getId(), offerDaoMapper.getOfferDetails(offer1).getId());
        assertEquals(offerDetailsList.get(0).getValidity(), offerDaoMapper.getOfferDetails(offer1).getValidity());
        assertNotNull(offerDaoMapper.getOfferDetails(offer1));
        assertNull(offerDaoMapper.getOfferDetails(null));
        offer1.setExpiryDate(null);
        assertNull(offerDaoMapper.getOfferDetails(null));
    }
}
