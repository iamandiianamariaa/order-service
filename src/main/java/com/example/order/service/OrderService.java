package com.example.order.service;

import com.example.order.domain.Courier;
import com.example.order.domain.Order;
import com.example.order.domain.enums.Status;
import com.example.order.dto.request.OrderRequestDto;
import com.example.order.exception.EntityNotFoundException;
import com.example.order.mapper.OrderMapper;
import com.example.order.repository.CourierRepository;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final CourierRepository courierRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public Optional<Order> create(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.maptoEntity(orderRequestDto);
        order.setStatus(Status.PLACED);
        Optional<Courier> courier = courierRepository.findAvailableCouriers(order.getSenderCity(), order.getSenderCounty(), order.getSenderCountry());
        if (courier.isEmpty())
            return Optional.empty();

        order.setCourier(courier.get());
        courierRepository.setNoOrders(courier.get().getId());
        order.setStatus(Status.CONFIRMED);
        return Optional.of(orderRepository.save(order));
    }

    public Order getById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> EntityNotFoundException.builder()
                        .entityId(orderId)
                        .entityType("Order")
                        .build()
                );
    }

    @Transactional
    public void deleteById(Long id) {
        Order order = getById(id);
        courierRepository.setNoOrdersPlus1(order.getCourier().getId());
        orderRepository.deleteById(id);
    }

    @Transactional
    public void updateOrderWhenCityIsChanged(OrderRequestDto orderRequestDto, Order order) {
        String orderRequestSenderCity = orderRequestDto.getSenderCity();
        String orderRequestSenderCounty = orderRequestDto.getSenderCounty();
        String orderRequestSenderCountry = orderRequestDto.getSenderCountry();
        String orderSenderCity = order.getSenderCity();
        String orderSenderCounty = order.getSenderCounty();
        String orderSenderCountry = order.getSenderCountry();
        Courier oldCourier = order.getCourier();
        Optional<Courier> newCourier = courierRepository.findAvailableCouriers(orderRequestSenderCity, orderRequestSenderCounty, orderRequestSenderCountry);
        if (!Objects.equals(orderRequestSenderCity, orderSenderCity) ||
                !Objects.equals(orderRequestSenderCounty, orderSenderCounty)
                || !Objects.equals(orderRequestSenderCountry, orderSenderCountry)) {
            if (newCourier.isPresent()) {
                courierRepository.setNoOrdersPlus1(oldCourier.getId());
                courierRepository.setNoOrders(newCourier.get().getId());
            }
        }
    }

    public Optional<Order> update(Long orderId, OrderRequestDto orderRequestDto) {
        Order order = getById(orderId);
        Order updatedOrder = orderMapper.update(orderRequestDto, order);
        Optional<Courier> newCourier = courierRepository.findAvailableCouriers(orderRequestDto.getSenderCity(), orderRequestDto.getSenderCounty(), orderRequestDto.getSenderCountry());
        updateOrderWhenCityIsChanged(orderRequestDto, order);
        if (newCourier.isEmpty()) return Optional.empty();
        return Optional.of(orderRepository.save(updatedOrder));
    }

    public List<Order> getAll(String username) {
        return orderRepository.findOrdersByUsername(username);
    }
}
