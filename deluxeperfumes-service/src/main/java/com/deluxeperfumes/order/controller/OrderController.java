package com.deluxeperfumes.order.controller;

import com.deluxeperfumes.order.dto.OrderDto;
import com.deluxeperfumes.order.endpoint.OrderEndpoint;
import com.deluxeperfumes.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController implements OrderEndpoint {

    private final OrderService orderService;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @Override
    public void updateOrder(OrderDto orderDto, UUID orderExternalId) {
        orderService.updateOrder(orderDto, orderExternalId);
    }

    @Override
    public void deleteOrder(UUID orderExternalId) {
        orderService.deleteOrder(orderExternalId);
    }

    @Override
    public Page<OrderDto> getOrders(String searchString, Pageable pageable) {
        return orderService.findAll(searchString, pageable);
    }

    @Override
    public OrderDto getOrderByExternalId(UUID orderExternalId) {
        return orderService.getOrderByExternalID(orderExternalId);
    }
}
