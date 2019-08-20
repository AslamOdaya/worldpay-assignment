package com.worldpay.assignment.message;

public class OfferRequestMessage {

    private String offerName;
    private String offerDescription;
    private String offerStartDate;
    private String offerExpiryDate;
    private String currency;

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

    public String getOfferStartDate() {
        return offerStartDate;
    }

    public void setOfferStartDate(String offerStartDate) {
        this.offerStartDate = offerStartDate;
    }

    public String getOfferExpiryDate() {
        return offerExpiryDate;
    }

    public void setOfferExpiryDate(String offerExpiryDate) {
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
        return "{\"offerName\":\"" + offerName + "\"," +
                "\"offerDescription\":\"" + offerDescription + "\"," +
                "\"offerStartDate\":\"" + offerStartDate + "\"," +
                "\"offerExpiryDate\":\"" + offerExpiryDate + "\"," +
                "\"currency\":\"" + currency + "\"}";
    }
}
