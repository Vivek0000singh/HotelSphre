package com.hotel.dto;

import lombok.Data;

@Data
public class RoomRequestDTO {
    private String roomNumber;
    private int floor;
    private String status;
    private Long roomTypeId;
}
