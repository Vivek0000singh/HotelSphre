package com.hotel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.BookingRequestDTO;
import com.hotel.dto.BookingResponseDTO;
import com.hotel.entity.Booking;
import com.hotel.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // CREATE BOOKING (DTO based)
    @PostMapping("/create")
    public ResponseEntity<BookingResponseDTO> createBooking(
            @RequestBody BookingRequestDTO request) {

        Booking booking = bookingService.createBooking(
                request.getUserId(),
                request.getRoomId(),
                request.getCheckInDate(),
                request.getCheckOutDate()
        );

        BookingResponseDTO response = new BookingResponseDTO();
        response.setBookingId(booking.getBookingId());
        response.setUserName(booking.getUser().getFullName());
        response.setRoomNumber(booking.getRoom().getRoomNumber());
        response.setCheckInDate(booking.getCheckInDate());
        response.setCheckOutDate(booking.getCheckOutDate());
        response.setBookingStatus(booking.getBookingStatus());
        response.setTotalAmount(booking.getTotalAmount()); // 🔥 THIS WAS MISSING

        return ResponseEntity.ok(response);
    }

    // GET BOOKINGS BY USER (still OK)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
    }
}

