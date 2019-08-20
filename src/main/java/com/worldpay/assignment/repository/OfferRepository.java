package com.worldpay.assignment.repository;

import com.worldpay.assignment.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {


    public Optional<Offer> findByOfferName(String offerName);
}
