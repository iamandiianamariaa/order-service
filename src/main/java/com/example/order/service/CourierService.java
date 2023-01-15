package com.example.order.service;

import com.example.order.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierRepository courierRepository;
}
