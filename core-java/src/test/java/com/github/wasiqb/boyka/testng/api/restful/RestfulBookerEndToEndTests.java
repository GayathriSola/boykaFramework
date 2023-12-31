/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.testng.api.restful;

import static com.github.wasiqb.boyka.actions.api.ApiActions.withRequest;
import static com.github.wasiqb.boyka.enums.PlatformType.API;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.testng.api.restful.requests.BookingRequest.createBooking;
import static com.github.wasiqb.boyka.testng.api.restful.requests.BookingRequest.deleteBooking;
import static com.github.wasiqb.boyka.testng.api.restful.requests.BookingRequest.getBooking;
import static com.github.wasiqb.boyka.testng.api.restful.requests.BookingRequest.updateBooking;
import static com.github.wasiqb.boyka.testng.api.restful.requests.BookingRequest.updatePartialBooking;

import com.github.wasiqb.boyka.exception.FrameworkError;
import com.github.wasiqb.boyka.testng.api.restful.requests.BookingData;
import com.github.wasiqb.boyka.testng.api.restful.requests.BookingDataBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for testing Api manager class.
 *
 * @author Wasiq Bhamla
 * @since 28-Feb-2023
 */
@Epic ("API Test")
@Feature ("Restful Booker Test class")
public class RestfulBookerEndToEndTests {
    private static final String BOOKING_ID = "bookingId";

    private BookingDataBuilder dataBuilder;
    private BookingData        newBooking;

    /**
     * Setup API Test.
     */
    @Description ("Setup Test Class")
    @BeforeClass (description = "Setup test class", alwaysRun = true)
    public void setupTestClass () {
        createSession (API, "test_restfulbooker");
        this.dataBuilder = new BookingDataBuilder ();
        this.newBooking = this.dataBuilder.bookingDataBuilder ();
    }

    /**
     * Clean up Test class.
     */
    @AfterClass (description = "Tear down test class", alwaysRun = true)
    public void tearDownTestClass () {
        clearSession ();
    }

    @Story ("Create Booking with POST request")
    @Test (description = "Test for creating new booking with POST request")
    public void testCreateBooking () {
        final var request = createBooking (this.newBooking);
        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyStatusMessage ()
            .isEqualTo ("OK");
        response.verifySchema ("create-booking-schema.json");
        response.verifyTextField ("bookingid")
            .isNotNull ();
        response.verifyTextField ("booking.firstname")
            .isEqualTo (this.newBooking.getFirstname ());
        response.verifyBooleanField ("booking.depositpaid")
            .isTrue ();
        response.verifyHeader ("Content-Type")
            .isEqualTo ("application/json; charset=utf-8");
        final var bookingId = response.getResponseData ("bookingid");
        getSession ().setSharedData (BOOKING_ID, bookingId);
    }

    @Story ("Delete Booking")
    @Test (description = "Test for Deleting a booking using DELETE request")
    public void testDeleteBooking () {
        final var response = withRequest (deleteBooking (getSession ().getSharedData (BOOKING_ID))).execute ();
        response.verifyStatusCode ()
            .isEqualTo (201);
    }

    @Story ("Get deleted Booking")
    @Test (description = "Test for checking deleted booking using GET request")
    public void testDeletedBooking () {
        final var response = withRequest (getBooking (getSession ().getSharedData (BOOKING_ID))).execute ();
        response.verifyStatusCode ()
            .isEqualTo (404);
    }

    @Story ("Get Created Booking")
    @Test (description = "Test for retrieving booking using GET request")
    public void testGetBooking () {
        final var response = withRequest (getBooking (getSession ().getSharedData (BOOKING_ID))).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (this.newBooking.getFirstname ());
        response.verifyTextField ("lastname")
            .isEqualTo (this.newBooking.getLastname ());
    }

    @Story ("Json Validation Exception tests")
    @Test (description = "Tests for file not found exception", expectedExceptions = FrameworkError.class)
    public void testJsonSchemaFileException () {
        final var request = createBooking (this.newBooking);
        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyStatusMessage ()
            .isEqualTo ("OK");
        response.verifySchema ("create-booking-scheme.json");
    }

    @Story ("Update Booking")
    @Test (description = "Test for Updating booking using PUT request")
    public void testUpdateBooking () {
        final var updateBookingData = this.dataBuilder.bookingDataBuilder ();

        final var response = withRequest (
            updateBooking (getSession ().getSharedData (BOOKING_ID), updateBookingData)).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (updateBookingData.getFirstname ());
        response.verifyTextField ("lastname")
            .isEqualTo (updateBookingData.getLastname ());
    }

    @Story ("Update Partial Booking")
    @Test (description = "Test for partial updating booking using PATCH request")
    public void testUpdatePartialBooking () {
        final var partialBookingData = this.dataBuilder.partialBookingBuilder ();

        final var response = withRequest (
            updatePartialBooking (getSession ().getSharedData (BOOKING_ID), partialBookingData)).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (partialBookingData.getFirstname ());
        response.verifyIntField ("totalprice")
            .isEqualTo (partialBookingData.getTotalprice ());
    }
}
