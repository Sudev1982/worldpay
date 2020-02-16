package com.task.worldpay.worldpayTask.service;

import com.task.worldpay.worldpayTask.dao_service.OfferDaoService;
import com.task.worldpay.worldpayTask.model.response.AllOffers;
import com.task.worldpay.worldpayTask.offer.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OfferManagerServiceTest {

    @Autowired
    private OfferManagerService offerManagerService;

    @MockBean
    private OfferDaoService offerDaoService;

    private Offer offer;
    private AllOffers allOffers;
    AllOffers allOffer = null;

    @BeforeEach
    public void setUp() {
        offer = new Offer();
        offer.setOfferName("offerTest1");
        offer.setOfferDetailDescription("When you buy for 30 pounds get 2 pounds off");
        offer.setExpiryDate("2021-02-27T15:39:46.058");

        allOffers = new AllOffers();
        allOffers.setCurrency("GBP");
        allOffers.setOfferDescription("Sale On");
        List<Offer> offerList = new ArrayList<>();
        Offer offer = new Offer();
        offer.setOfferName("offer1");
        offer.setOfferDetailDescription("Sale On : When you buy for 30 pounds get 2 pounds off");
        offer.setExpiryDate("27 February 2021 15:39:46");
        offerList.add(offer);
        allOffers.setOfferModelList(offerList);
    }


    @Test
    @DisplayName("createNewOfferServiceTesting")
    public void createNewOfferTest() {
        String expected = "Created/Updated Successfully";
        Mockito.when(offerDaoService.createNewOffer(offer)).thenReturn(expected);
        assertEquals(expected, offerManagerService.createNewOffer(offer));
    }

    @Test
    @DisplayName("createNewOfferServiceFaliureTesting")
    public void createNewOfferFaliureTest() {
        String expected = "Not Successfully";
        Mockito.when(offerDaoService.createNewOffer(offer)).thenReturn(expected);
        assertEquals(expected, offerManagerService.createNewOffer(offer));
    }

    @Test
    @DisplayName("getAllActiveOffersTesting")
    public void getAllActiveOffersTest() {
        Mockito.when(offerDaoService.getAllOffers()).thenReturn(allOffers);
        assertEquals(allOffers, offerManagerService.getAllActiveOffers());
    }

    @Test
    @DisplayName("cancelOfferTesting")
    public void cancelOfferTest() {
        offerManagerService.cancelOffer("offerTest1");
        Mockito.when(offerDaoService.findOffer("offerTest1")).thenReturn(null);
        assertEquals(allOffer, offerManagerService.findOffer("offerTest1"));
    }


}
