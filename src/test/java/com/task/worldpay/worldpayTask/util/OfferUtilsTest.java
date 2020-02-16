package com.task.worldpay.worldpayTask.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OfferUtilsTest {

    @Test
    @DisplayName("formatDateUtilTesting")
    public void formatDateTest() {
        assertEquals("27 February 2021 15:39:46", OfferUtils.formatDate(LocalDateTime.parse("2021-02-27T15:39:46.058")));
        assertEquals("", OfferUtils.formatDate(null));
    }
}
