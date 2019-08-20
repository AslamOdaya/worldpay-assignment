package com.worldpay.assignment.exception;

import com.worldpay.assignment.validation.ErrorResponse;
import com.worldpay.assignment.validation.OfferValidationCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class OfferExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(OfferNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleOfferNotFoundException() {

        ErrorResponse error = new ErrorResponse(OfferValidationCode.offerNotFound, "Offer not found.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OfferExpiredException.class)
    public final ResponseEntity<ErrorResponse> handleOfferExpiredException() {

        ErrorResponse error = new ErrorResponse(OfferValidationCode.offerExpired, "Offer has expired.");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OfferExistsException.class)
    public final ResponseEntity<ErrorResponse> handleOfferExistsException() {

        ErrorResponse error = new ErrorResponse(OfferValidationCode.duplicateOffer, "This offer already exists.");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoMessageException.class)
    public final ResponseEntity<ErrorResponse> handleNoMessageException() {

        ErrorResponse error = new ErrorResponse(OfferValidationCode.noMessageProvided, "No offer request message provided.");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

}
