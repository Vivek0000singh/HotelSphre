package com.hotel.service;

import java.time.LocalDate;
import java.util.List;

import com.hotel.entity.Booking;

public interface BookingService {

    Booking createBooking(Long userId, Long roomId,
                           LocalDate checkIn, LocalDate checkOut);

    List<Booking> getBookingsByUser(Long userId);
}
