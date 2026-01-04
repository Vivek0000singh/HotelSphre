package com.hotel.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entity.Payment;
import com.hotel.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<Payment> makePayment(
            @RequestParam Long bookingId,
            @RequestParam BigDecimal amount,
            @RequestParam String method
    ) {
        Payment payment = paymentService.createPayment(bookingId, amount, method);
        return ResponseEntity.ok(payment);
    }
}
