package com.task.worldpay.worldpayTask.repository;

import com.task.worldpay.worldpayTask.model.persist.OfferDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OfferRepoTest {

    @Autowired
    private OfferRepo offerRepo;

    private OfferDetails offerDetails;

    @BeforeEach
    public void setUp() {
        offerDetails = new OfferDetails();
        offerDetails.setId("offer1");
        offerDetails.setOfferDetails("Sale On - When you buy for 30 pounds get 2 pounds off");
        offerDetails.setCurrency("GBP");
        offerDetails.setValidity(LocalDateTime.parse("2021-02-27T15:39:46.058"));
    }

    @Test
    @DisplayName("saveJpaApiTesting")
    public void saveTest() {
        OfferDetails persisted = offerRepo.save(offerDetails);
        Optional<OfferDetails> offerDetails = offerRepo.findById("offer1");
        assertNotNull(offerDetails.get());
        assertEquals(persisted, offerDetails.get());
    }

    @Test
    @DisplayName("updateJpaApiTesting")
    public void updateTest() {
        OfferDetails persisted = offerRepo.save(offerDetails);
        Optional<OfferDetails> offerDetail = offerRepo.findById("offer1");
        assertNotNull(offerDetail.get());
        assertEquals(persisted, offerDetail.get());
        offerDetails.setValidity(LocalDateTime.parse("2021-02-27T15:39:46.058"));
        Optional<OfferDetails> offerDetailUpdated = offerRepo.findById("offer1");
        assertNotNull(offerDetailUpdated.get());
        assertEquals(persisted, offerDetailUpdated.get());
    }

    @Test
    @DisplayName("findAllActiveOffersJpaApiTesting")
    public void findAllActiveOffersTest() {
        offerRepo.save(offerDetails);
        List<OfferDetails> offerDetailsList = offerRepo.findAllActiveOffers(LocalDateTime.now());
        assertNotNull(offerDetailsList);
    }

    @Test
    @DisplayName("deleteOfferJpaApiTesting")
    public void deleteOfferTest() {
        offerRepo.save(offerDetails);
        offerRepo.deleteById("offer1");
        Optional<OfferDetails> offerDetails = offerRepo.findById("offer1");
        assertFalse(offerDetails.isPresent());
    }


}
