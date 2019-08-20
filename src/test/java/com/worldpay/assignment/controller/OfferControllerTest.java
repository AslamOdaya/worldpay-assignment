package com.worldpay.assignment.controller;


import com.worldpay.assignment.message.OfferRequestMessage;
import com.worldpay.assignment.model.Offer;
import com.worldpay.assignment.repository.OfferRepository;
import com.worldpay.assignment.service.OfferService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class OfferControllerTest {

    @Autowired
    MockMvc mockMvc;


    @MockBean
    OfferService mockOfferService;

    @MockBean
    OfferRepository offerRepository;


    @Test
    public void testCreateOffer_whenOfferMessageIsProvided_newOfferResponseIsCreated() throws Exception {

        OfferRequestMessage offerRequestMessage = new OfferRequestMessage();

        offerRequestMessage.setOfferName("20% off");
        offerRequestMessage.setOfferDescription("Add 20% off on item");
        offerRequestMessage.setOfferStartDate("2019-08-10");
        offerRequestMessage.setOfferExpiryDate("2019-08-20");
        offerRequestMessage.setCurrency("GBP");

        Offer offer = createOffer(LocalDate.parse("2019-08-10"), LocalDate.parse("2019-08-20"));
        when(mockOfferService.createNewOffer(any())).thenReturn(offer);
        MvcResult result = this.mockMvc.perform(post("/offers/").contentType(MediaType.APPLICATION_JSON).content(offerRequestMessage.toString()))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();



        Assert.assertEquals(offer.toString(),content);

    }

    @Test
    public void testGetOffer_whenrRequestedWithOfferId_offerResponseIsReturned() throws Exception {


        Offer offer = createOffer(LocalDate.parse("2019-08-10"), LocalDate.parse("2019-08-20"));

        when(mockOfferService.getOfferById(anyInt())).thenReturn(offer);
        MvcResult result = this.mockMvc.perform(get("/offers/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        Assert.assertEquals(offer.toString(),content);

    }

    @Test
    public void testCancelOffer_whenRequestedWithOfferId_cancelledOfferResponseIsReturned()throws Exception {


        Offer offer = createOffer(LocalDate.parse("2019-08-10"), LocalDate.now());

        when(mockOfferService.cancelOfferById(anyInt())).thenReturn(offer);

        MvcResult result = this.mockMvc.perform(put("/offers/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        Assert.assertEquals(offer.toString(),content);

    }

    private Offer createOffer(LocalDate offerStartDate, LocalDate offerExpiryDate) {
        Offer offer = new Offer();

        offer.setId(1);
        offer.setOfferName("20% off");
        offer.setOfferDescription("Add 20% off on item");
        offer.setOfferStartDate(offerStartDate);
        offer.setOfferExpiryDate(offerExpiryDate);
        offer.setCurrency("GBP");

        return offer;
    }




}
