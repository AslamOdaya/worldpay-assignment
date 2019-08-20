package com.worldpay.assignment.service;

import com.worldpay.assignment.exception.NoMessageException;
import com.worldpay.assignment.exception.OfferExistsException;
import com.worldpay.assignment.exception.OfferExpiredException;
import com.worldpay.assignment.exception.OfferNotFoundException;
import com.worldpay.assignment.message.OfferRequestMessage;
import com.worldpay.assignment.model.Offer;
import com.worldpay.assignment.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public Offer createNewOffer(OfferRequestMessage offerRequestMessage) {

        if(offerRequestMessage == null)
            throw new NoMessageException();

        offerRepository.findByOfferName(offerRequestMessage.getOfferName())
                .ifPresent(s -> {
                    throw new OfferExistsException();
                });

        Offer offer = new Offer();
        offer.setOfferName(offerRequestMessage.getOfferName());
        offer.setOfferDescription(offerRequestMessage.getOfferDescription());
        offer.setOfferStartDate(LocalDate.parse(offerRequestMessage.getOfferStartDate()));
        offer.setOfferExpiryDate(LocalDate.parse(offerRequestMessage.getOfferExpiryDate()));
        offer.setCurrency(offerRequestMessage.getCurrency());


        return offerRepository.save(offer);
    }

    public Offer getOfferById(Integer id) {


        Offer offer = offerRepository.findById(id).orElseThrow(OfferNotFoundException::new);

        if (offer.getOfferExpiryDate().isBefore(LocalDate.now()))
            throw new OfferExpiredException();


        return offer;
    }

    public Offer cancelOfferById(Integer id) {

        Optional<Offer> offer = Optional.ofNullable(offerRepository.getOne(id));
        Offer offerToCancel = offer.orElseThrow(OfferNotFoundException::new);
        offerToCancel.setOfferExpiryDate(LocalDate.now());


        return offerRepository.save(offerToCancel);
    }
}
