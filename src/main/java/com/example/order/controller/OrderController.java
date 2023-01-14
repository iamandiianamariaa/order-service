package com.example.order.controller;

import com.example.order.domain.Order;
import com.example.order.dto.request.OrderRequestDto;
import com.example.order.dto.response.OrderDto;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/orders")
@Validated
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid OrderRequestDto dto){
        return orderMapper.mapToDto(orderService.create(dto));
    }

    @GetMapping
    public List<OrderDto> getAll(){
        return orderService.getAll().stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{orderId}")
    public OrderDto getById(@PathVariable Long orderId){
        return orderMapper.mapToDto(orderService.getById(orderId));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId,
                                                @RequestBody @Valid OrderRequestDto orderRequestDto) {
        Order savedOrder = orderService.update(orderId, orderRequestDto);
        return ResponseEntity
                .ok()
                .body(orderMapper.mapToDto(savedOrder));
    }

    @PutMapping("/orderStatus/{orderId}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long orderId,
                                                      @RequestParam(value = "status") String status) {
        Order savedOrder = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity
                .ok()
                .body(orderMapper.mapToDto(savedOrder));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteById(@PathVariable Long orderId) {

        orderService.deleteById(orderId);

        return ResponseEntity
                .noContent()
                .build();
    }
}