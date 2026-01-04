package com.hotel.service;

import java.util.List;

import com.hotel.entity.Room;

public interface RoomService {

    List<Room> getAvailableRooms();
}
