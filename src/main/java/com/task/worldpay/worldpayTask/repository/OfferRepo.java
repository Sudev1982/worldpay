package com.task.worldpay.worldpayTask.repository;

import com.task.worldpay.worldpayTask.model.persist.OfferDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OfferRepo extends CrudRepository<OfferDetails, String> {

    @Query("SELECT o FROM OfferDetails o WHERE o.validity > :validityDate")
    List<OfferDetails> findAllActiveOffers(@Param("validityDate") LocalDateTime dateTime);


}
