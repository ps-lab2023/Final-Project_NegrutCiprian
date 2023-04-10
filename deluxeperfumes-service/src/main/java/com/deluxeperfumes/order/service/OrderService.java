package com.deluxeperfumes.order.service;


import com.deluxeperfumes.order.dto.OrderDto;
import com.deluxeperfumes.order.entity.Order;
import com.deluxeperfumes.order.exceptions.OrderNotFoundException;
import com.deluxeperfumes.order.mapper.OrderMapper;
import com.deluxeperfumes.order.repository.OrderRepository;
import com.deluxeperfumes.perfume.exceptions.PerfumeNotFoundException;
import com.deluxeperfumes.perfume.repository.PerfumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService extends Observable {

    private final OrderRepository orderRepository;
    private final PerfumeRepository perfumeRepository;
    private final OrderMapper orderMapper;

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        order.setPerfumes(new ArrayList<>());
        order.setIdentifier(order.getUsername().substring(0, 4) + LocalTime.now().getNano());
        order.setCreatedDate(LocalDate.now());
        if(!orderDto.getPerfumeNames().isEmpty()) {
            orderDto.getPerfumeNames().forEach(name -> {
                var perfume = perfumeRepository.findPerfumeByName(name).orElseThrow(
                        () -> new PerfumeNotFoundException("Could not find perfume with name: " + name)
                );
                order.getPerfumes().add(perfume);
                order.setOrderPrice(order.getOrderPrice() + perfume.getPrice());
            });
        }
        var orderSaved = orderRepository.save(order);
        orderSaved.getPerfumes().forEach(perfume -> {
            perfume.setOrder(orderSaved);
        });

        this.addObserver(order);
        setChanged();
        notifyObservers("Order successfully created!");

        return orderMapper.toDto(orderSaved);
    }

    public void updateOrder(OrderDto orderUpdated, UUID orderExternalId) {
        var orderToUpdate = orderRepository.findOrderByExternalId(orderExternalId)
                .orElseThrow(() -> new OrderNotFoundException(
                        "Could not find order with externalId " + orderExternalId));
        if(!orderUpdated.getPerfumeNames().isEmpty()) {
            orderUpdated.getPerfumeNames().forEach(name -> {
                var perfume = perfumeRepository.findPerfumeByName(name).orElseThrow(
                        () -> new PerfumeNotFoundException("Could not find perfume with name: " + name)
                );
                orderToUpdate.getPerfumes().add(perfume);
            });
        }
        orderToUpdate.setIdentifier(orderUpdated.getUsername().substring(0, 4) + LocalTime.now().getNano());
        orderToUpdate.setUpdatedDate(LocalDate.now());
        var orderSaved = orderRepository.save(orderToUpdate);
        orderSaved.getPerfumes().forEach(perfume -> {
            perfume.setOrder(orderSaved);
        });
    }

    public void deleteOrder(UUID orderExternalId) {
        orderRepository.deleteOrderByExternalId(orderExternalId);
    }

    public Page<OrderDto> findAll(String searchTerm, Pageable pageable) {
        if (searchTerm.isEmpty()) {
            return orderRepository.findAll(pageable).map(orderMapper::toDto);
        }
        return orderRepository.findAllByIdentifierContainingIgnoreCaseOrUsernameContainingIgnoreCase(
                searchTerm, searchTerm, pageable).map(orderMapper::toDto);
    }

    public OrderDto getOrderByExternalID(UUID orderExternalID) {
        return orderMapper.toDto(orderRepository.findOrderByExternalId(orderExternalID)
                .orElseThrow(() -> new OrderNotFoundException(
                        "Could not find order with externalId: " + orderExternalID)));
    }
}