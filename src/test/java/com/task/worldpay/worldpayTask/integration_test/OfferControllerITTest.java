package com.task.worldpay.worldpayTask.integration_test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerITTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("createNewOfferITTesting")
    public void createNewOfferITTest() throws Exception {
        String expected = "Created/Updated Successfully";
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
    @DisplayName("getAllActiveOffersITTesting")
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
        String newOffer = "{\n" +
                "  \"offerName\": \"offer1\",\n" +
                "  \"offerDetailDescription\": \"When you buy for 30 pounds get 2 pounds off\",\n" +
                "  \"expiryDate\": \"2021-02-27T15:39:46.058\"\n" +
                "\n" +
                "}";
        RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post("/oapi/offer")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(newOffer)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        mockMvc.perform(postRequestBuilder);

        RequestBuilder getRequestBuilder = get("/oapi/offers").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(getRequestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(MediaType.APPLICATION_JSON_VALUE, result.getResponse().getContentType());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    @DisplayName("cancelOfferITTesting")
    public void cancelOfferITTest() throws Exception {
        String newOffer = "{\n" +
                "  \"offerName\": \"offer1\",\n" +
                "  \"offerDetailDescription\": \"When you buy for 30 pounds get 2 pounds off\",\n" +
                "  \"expiryDate\": \"2021-02-27T15:39:46.058\"\n" +
                "\n" +
                "}";
        RequestBuilder postRequestBuilder = MockMvcRequestBuilders.post("/oapi/offer")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(newOffer)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        mockMvc.perform(postRequestBuilder).andReturn();

        RequestBuilder delRequestBuilder = MockMvcRequestBuilders.delete("/oapi/offer/offer1");
        MvcResult result = mockMvc.perform(delRequestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        RequestBuilder getRequestBuilder = get("/oapi/offers").accept(MediaType.APPLICATION_JSON);
        MvcResult searchResult = mockMvc.perform(getRequestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals("", result.getResponse().getContentAsString());
    }


    @Test
    @DisplayName("createNewOfferExceptionTesting")
    public void createNewOfferExceptionTests() throws Exception {
        String expected = "{\"requestUri\":\"/oapi/offer\",\"errorMessage\":\"Error Occured\"}";
        String newOffer = "{\n" +
                "  \"offerNames\": \"offer1\",\n" +
                "  \"offerDetailDescription\": \"When you buy for 30 pounds get 2 pounds off\",\n" +
                "  \"expiryDates\": \"2021-02-27T15:39:46.058\"\n" +
                "\n" +
                "}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/oapi/offer")
                .accept(MediaType.APPLICATION_JSON_VALUE).content(newOffer)
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals(expected, response.getContentAsString());

    }
}
