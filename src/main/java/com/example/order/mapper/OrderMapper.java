package com.example.order.mapper;

import com.example.order.domain.Order;
import com.example.order.dto.request.OrderRequestDto;
import com.example.order.dto.response.OrderDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OrderMapper {
    OrderDto mapToDto(Order order);

    //@Mapping(source = "orderDetailsId", target = "orderDetails", qualifiedByName = "idToOrderDetails")
    Order mapToEntity(OrderDto orderDto);

    Order maptoEntity(OrderRequestDto orderRequestDto);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "pickupDate")
    Order update(OrderRequestDto orderRequestDto, @MappingTarget Order order);
}
