package com.hotel.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.hotel.dto.BookingServiceRequestDTO;
import com.hotel.entity.Booking;
import com.hotel.entity.BookingService;
import com.hotel.entity.HotelService;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repository.BookingRepository;
import com.hotel.repository.BookingServiceRepository;
import com.hotel.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServicesManagerServiceImpl implements BookingServicesManagerService {

    private final BookingRepository bookingRepository;
    private final ServiceRepository serviceRepository;
    private final BookingServiceRepository bookingServiceRepository;

    @Override
    public BookingService addServiceToBooking(BookingServiceRequestDTO request) {

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with id: " + request.getBookingId()));

        HotelService service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service not found with id: " + request.getServiceId()));

        BigDecimal pricePerUnit = service.getPrice();
        BigDecimal totalPrice =
                pricePerUnit.multiply(BigDecimal.valueOf(request.getQuantity()));

        BookingService bs = new BookingService();
        bs.setBooking(booking);
        bs.setService(service);
        bs.setQuantity(request.getQuantity());
        bs.setPricePerUnit(pricePerUnit);
        bs.setTotalPrice(totalPrice);
        bs.setServiceDate(request.getServiceDate());

        BookingService saved = bookingServiceRepository.save(bs);

        // 🔥 UPDATE BOOKING TOTAL AMOUNT
        BigDecimal currentTotal =
                booking.getTotalAmount() == null ? BigDecimal.ZERO : booking.getTotalAmount();

        booking.setTotalAmount(currentTotal.add(totalPrice));
        bookingRepository.save(booking);

        return saved;
    }
}
