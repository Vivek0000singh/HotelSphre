package com.hotel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotel.entity.RoomType;
import com.hotel.repository.RoomTypeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/room-types")
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomTypeRepository roomTypeRepository;

    // CREATE ROOM TYPE
    @PostMapping
    public ResponseEntity<RoomType> createRoomType(@RequestBody RoomType roomType) {

        RoomType saved = roomTypeRepository.save(roomType);
        return ResponseEntity.ok(saved);   // 🔥 IMPORTANT
    }

    // GET ALL ROOM TYPES
    @GetMapping
    public ResponseEntity<List<RoomType>> getAllRoomTypes() {
        return ResponseEntity.ok(roomTypeRepository.findAll());
    }
}
