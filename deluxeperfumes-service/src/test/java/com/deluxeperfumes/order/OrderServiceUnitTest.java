package com.deluxeperfumes.order;


import com.deluxeperfumes.order.dto.OrderDto;
import com.deluxeperfumes.order.entity.Order;
import com.deluxeperfumes.order.mapper.OrderMapper;
import com.deluxeperfumes.order.repository.OrderRepository;
import com.deluxeperfumes.order.service.OrderService;
import com.deluxeperfumes.perfume.dto.PerfumeDto;
import com.deluxeperfumes.perfume.entity.Perfume;
import com.deluxeperfumes.perfume.repository.PerfumeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class OrderServiceUnitTest {

    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private  PerfumeRepository perfumeRepository;

    private OrderService orderService;

    @BeforeEach
    public void setup() {
        orderService = new OrderService(
                orderRepository, perfumeRepository, orderMapper);
    }

    @Test
    void when_create_order_return_order() {
        List<String> perfumeNames = new ArrayList<>();
        perfumeNames.add("Dior");

        List<Perfume> perfumes = new ArrayList<>();

        PerfumeDto perfumeDto = new PerfumeDto();
        perfumeDto.setName("Dior");
        perfumeDto.setExternalId(UUID.randomUUID());

        Perfume perfume = new Perfume();
        perfume.setName("Dior");
        perfume.setExternalId(perfumeDto.getExternalId());
        perfumes.add(perfume);

        OrderDto orderDto = new OrderDto();
        orderDto.setUsername("cipringr");
        orderDto.setExternalId(UUID.randomUUID());

        Order order = new Order();
        orderDto.setUsername("cipringr");
        order.setExternalId(orderDto.getExternalId());
        order.setPerfumes(perfumes);

        orderDto.setPerfumeNames(perfumeNames);

        lenient().when(perfumeRepository.findPerfumeByName(any(String.class)))
                .thenReturn(Optional.of(perfume));
        lenient().when(perfumeRepository.save(any(Perfume.class)))
                .thenReturn(perfume);

        lenient().when(orderRepository.findOrderByExternalId(any(UUID.class)))
                .thenReturn(Optional.of(order));
        lenient().when(orderRepository.save(any(Order.class)))
                .thenReturn(order);

        OrderDto result = orderService.createOrder(orderDto);

        assertEquals(orderDto.getExternalId(), result.getExternalId());
    }

}
