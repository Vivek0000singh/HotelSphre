package com.hotel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.BookingServiceRequestDTO;
import com.hotel.entity.BookingService;
import com.hotel.service.BookingServicesManagerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/booking-services")
@RequiredArgsConstructor
public class BookingServicesController {

    private final BookingServicesManagerService bookingServicesManagerService;

    @PostMapping
    public ResponseEntity<BookingService> addService(
            @RequestBody BookingServiceRequestDTO request) {

        return ResponseEntity.ok(
                bookingServicesManagerService.addServiceToBooking(request)
        );
    }
}
