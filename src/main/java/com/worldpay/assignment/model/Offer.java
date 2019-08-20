package com.worldpay.assignment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String offerName;
    private String offerDescription;
    private LocalDate offerStartDate;
    private LocalDate offerExpiryDate;
    private String currency;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public LocalDate getOfferStartDate() {
        return offerStartDate;
    }

    public void setOfferStartDate(LocalDate offerStartDate) {
        this.offerStartDate = offerStartDate;
    }

    public LocalDate getOfferExpiryDate() {
        return offerExpiryDate;
    }

    public void setOfferExpiryDate(LocalDate offerExpiryDate) {
        this.offerExpiryDate = offerExpiryDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {

        return "{\"id\":"+id+"," +
                "\"offerName\":\""+offerName+"\"," +
                "\"offerDescription\":\""+offerDescription+"\"," +
                "\"offerStartDate\":\""+offerStartDate+"\"," +
                "\"offerExpiryDate\":\""+offerExpiryDate+"\"," +
                "\"currency\":\""+currency+"\"}";
    }
}
