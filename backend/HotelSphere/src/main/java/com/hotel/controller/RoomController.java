package com.hotel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.RoomRequestDTO;
import com.hotel.entity.Room;
import com.hotel.entity.RoomType;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repository.RoomRepository;
import com.hotel.repository.RoomTypeRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody RoomRequestDTO dto) {

        RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("RoomType not found"));

        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setFloor(dto.getFloor());
        room.setStatus(dto.getStatus());
        room.setRoomType(roomType);

        return ResponseEntity.ok(roomRepository.save(room));
    }

    @GetMapping
    public ResponseEntity<?> getAllRooms() {
        return ResponseEntity.ok(roomRepository.findAll());
    }
}
