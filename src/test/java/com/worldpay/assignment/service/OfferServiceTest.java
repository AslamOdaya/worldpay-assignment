package com.worldpay.assignment.service;


import com.worldpay.assignment.exception.NoMessageException;
import com.worldpay.assignment.exception.OfferExistsException;
import com.worldpay.assignment.exception.OfferExpiredException;
import com.worldpay.assignment.message.OfferRequestMessage;
import com.worldpay.assignment.model.Offer;
import com.worldpay.assignment.repository.OfferRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferServiceTest {

    @InjectMocks
    OfferService offerService;

    @Mock
    OfferRepository mockOfferRepository;

    @Mock
    OfferRequestMessage mockOfferRequestMessage;


    @BeforeAll
    public void setup() {

        offerService = new OfferService();
    }

    @Test
    public void testCreate_whenOfferMessageIsProvided_newOfferResponseIsCreated() {


        Offer expectedOffer = createOffer(LocalDate.parse("2019-08-10"), LocalDate.parse("2019-08-20"));


        when(mockOfferRequestMessage.getOfferStartDate()).thenReturn(expectedOffer.getOfferStartDate().toString());
        when(mockOfferRequestMessage.getOfferExpiryDate()).thenReturn(expectedOffer.getOfferExpiryDate().toString());

        when(mockOfferRepository.save(any())).thenReturn(expectedOffer);

        Offer actualOffer = offerService.createNewOffer(mockOfferRequestMessage);


        Assert.assertEquals(expectedOffer.toString(), actualOffer.toString());
    }

    @Test
    public void testCreate_whenOfferMessageIsProvided_duplicateOfferResponseErrorIsThrown() {


        Offer expectedOffer = createOffer(LocalDate.parse("2019-08-10"), LocalDate.parse("2019-08-20"));

        when(mockOfferRequestMessage.getOfferName()).thenReturn(expectedOffer.getOfferName());
        when(mockOfferRequestMessage.getOfferStartDate()).thenReturn(expectedOffer.getOfferStartDate().toString());
        when(mockOfferRequestMessage.getOfferExpiryDate()).thenReturn(expectedOffer.getOfferExpiryDate().toString());

        Offer offer = createOffer(LocalDate.parse("2019-08-10"), LocalDate.parse("2019-08-20"));

        when(mockOfferRepository.findByOfferName(anyString())).thenReturn(Optional.of(offer));

        assertThrows(OfferExistsException.class, () -> offerService.createNewOffer(mockOfferRequestMessage));


    }

    @Test
    public void testCreate_whenNullOfferMessageIsProvided_NoMessageExceptionIsThrown() {


        assertThrows(NoMessageException.class, () -> offerService.createNewOffer(null));

    }


    @Test
    public void testGet_whenOfferIsQueriedWithId_OfferResponseIsSuccessfullyRetrieved() {

        Offer expectedOffer = createOffer(LocalDate.parse("2019-08-10"), LocalDate.parse("2019-08-20"));

        when(mockOfferRepository.findById(any())).thenReturn(Optional.of(expectedOffer));

        Offer actualOffer = offerService.getOfferById(1);

        Assert.assertEquals(expectedOffer.toString(), actualOffer.toString());

    }

    @Test
    public void testGet_whenExpiredOfferIsQueriedWithId_expiredOfferMessageIsThrown() {

        Offer offer = createOffer(LocalDate.parse("2019-07-10"), LocalDate.parse("2019-08-19"));

        when(mockOfferRepository.findById(anyInt())).thenReturn(Optional.ofNullable(offer));

        assertThrows(OfferExpiredException.class, () -> offerService.getOfferById(1));

    }

    @Test
    public void testCancel_whenOfferIsCancelled_retrieveOfferWithUpdatedExpiryDate() {


        Offer offer = createOffer(LocalDate.parse("2019-08-11"), LocalDate.parse("2019-08-20"));
        Offer expectedOffer = createOffer(LocalDate.parse("2019-08-10"), LocalDate.now());

        when(mockOfferRepository.save(any())).thenReturn(expectedOffer);
        when(mockOfferRepository.getOne(1)).thenReturn(offer);

        Offer actualOffer = offerService.cancelOfferById(1);
        Assert.assertEquals(expectedOffer.toString(), actualOffer.toString());

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
