package com.hotel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entity.HotelService;
import com.hotel.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceRepository serviceRepository;

    @PostMapping
    public ResponseEntity<HotelService> addService(@RequestBody HotelService service) {
        return ResponseEntity.ok(serviceRepository.save(service));
    }

    @GetMapping
    public ResponseEntity<List<HotelService>> getAllServices() {
        return ResponseEntity.ok(serviceRepository.findAll());
    }
}
