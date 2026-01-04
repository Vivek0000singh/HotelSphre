package com.hotel.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.entity.Booking;
import com.hotel.entity.Room;
import com.hotel.entity.User;
import com.hotel.exception.BadRequestException;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public Booking createBooking(
            Long userId,
            Long roomId,
            LocalDate checkIn,
            LocalDate checkOut) {

        if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
            throw new BadRequestException("Check-out date must be after check-in date");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room not found with id: " + roomId));

        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);

        BigDecimal pricePerNight = room.getRoomType().getBasePricePerNight();

        if (pricePerNight == null) {
            throw new BadRequestException("Room price per night is not configured");
        }

        BigDecimal roomCost =
                pricePerNight.multiply(BigDecimal.valueOf(nights));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckInDate(checkIn);
        booking.setCheckOutDate(checkOut);
        booking.setBookingStatus("CONFIRMED");
        booking.setTotalAmount(roomCost);   // 🔥 ROOM COST SET HERE
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUser_UserId(userId);
    }
}
