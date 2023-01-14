package com.example.order.service;

import com.example.order.domain.Order;
import com.example.order.domain.enums.Status;
import com.example.order.dto.request.OrderRequestDto;
import com.example.order.exception.EntityNotFoundException;
import com.example.order.mapper.OrderMapper;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Order create(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.maptoEntity(orderRequestDto);
        order.setStatus(Status.PLACED);
        order.setCost(10.5);

        return orderRepository.save(order);
    }

    public Order getById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> EntityNotFoundException.builder()
                        .entityId(orderId)
                        .entityType("Order")
                        .build()
                );
    }

    public void deleteById(Long id) {
        getById(id);
        orderRepository.deleteById(id);
    }

    public Order update(Long orderId, OrderRequestDto orderRequestDto) {
        Order order = getById(orderId);
        Order updatedOrder = orderMapper.update(orderRequestDto, order);

        return orderRepository.save(updatedOrder);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = getById(orderId);
        order.setStatus(Status.valueOf(status));

        return orderRepository.save(order);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
