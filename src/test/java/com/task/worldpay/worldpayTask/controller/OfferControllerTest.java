package com.task.worldpay.worldpayTask.controller;

import com.task.worldpay.worldpayTask.model.response.AllOffers;
import com.task.worldpay.worldpayTask.offer.Offer;
import com.task.worldpay.worldpayTask.service.OfferManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(value = OfferController.class)
public class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OfferManagerService offerManagerService;

    private AllOffers allOffers;


    @BeforeEach
    public void setUp() {
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
    @DisplayName("createNewOfferTesting")
    public void createOfferTest() throws Exception {
        String expected = "Created/Updated Successfully";
        Mockito.when(offerManagerService.createNewOffer(Mockito.any())).thenReturn(expected);
        String newOffer = "{\n" +
                "  \"offerName\": \"offer1\",\n" +
                "  \"offerDetailDescription\": \"When you buy for 30 pounds get 2 pounds off\",\n" +
                "  \"expiryDate\": \"2021-02-27T15:39:46.058\"\n" +
                "\n" +
                "}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/oapi/offer")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(newOffer)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());
    }

    @Test
    @DisplayName("createNewOfferUnsuccessfulTesting")
    public void unsuccessfulCreateOfferTest() throws Exception {
        String expected = "Not Successfully";
        Mockito.when(offerManagerService.createNewOffer(Mockito.any())).thenReturn(expected);
        String newOffer = "{\n" +
                "  \"offerName\": \"offer1\",\n" +
                "  \"offerDetailDescription\": \"When you buy for 30 pounds get 2 pounds off\",\n" +
                "  \"expiryDate\": \"2021-02-27T15:39:46.058\"\n" +
                "\n" +
                "}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/oapi/offer")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(newOffer)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());
    }


    @Test
    @DisplayName("getAllActiveOffersTest")
    public void getAllActiveOffersTest() throws Exception {
        String expected = "{\n" +
                "  \"currency\": \"GBP\",\n" +
                "  \"offerModelList\": [\n" +
                "    {\n" +
                "      \"offerName\": \"offer1\",\n" +
                "      \"offerDetailDescription\": \"Sale On : When you buy for 30 pounds get 2 pounds off\",\n" +
                "      \"expiryDate\": \"27 February 2021 15:39:46\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"offerDescription\": \"Sale On\"\n" +
                "}";

        Mockito.when(offerManagerService.getAllActiveOffers()).thenReturn(allOffers);
        RequestBuilder requestBuilder = get("/oapi/offers").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    @DisplayName("cancelOfferTesting")
    public void cancelOfferTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/oapi/offer/offerTest1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
