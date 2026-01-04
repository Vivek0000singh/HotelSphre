package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.entity.BookingService;

@Repository
public interface BookingServiceRepository extends JpaRepository<BookingService, Long> {
}
