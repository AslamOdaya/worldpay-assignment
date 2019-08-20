package com.worldpay.assignment.controller;

import com.worldpay.assignment.message.OfferRequestMessage;
import com.worldpay.assignment.model.Offer;
import com.worldpay.assignment.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offers")
public class OfferController {


    @Autowired
    OfferService offerService;

    @PostMapping("/")
    public Offer createOffer(@RequestBody OfferRequestMessage offerRequestMessage) {

        return offerService.createNewOffer(offerRequestMessage);
    }

    @GetMapping("/{id}")
    public Offer getOffer(@PathVariable("id") Integer id) {

        return offerService.getOfferById(id);
    }

    @PutMapping("/{id}")
    public Offer cancelOffer(@PathVariable("id") Integer id) {

        return offerService.cancelOfferById(id);
    }


}
