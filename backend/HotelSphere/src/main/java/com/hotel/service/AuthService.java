package com.hotel.service;

import com.hotel.dto.LoginRequestDTO;
import com.hotel.dto.RegisterRequestDTO;

public interface AuthService {
    String register(RegisterRequestDTO request);
    String login(LoginRequestDTO request);
}
