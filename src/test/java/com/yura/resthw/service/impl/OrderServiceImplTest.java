package com.yura.resthw.service.impl;

import com.yura.resthw.dao.OrderRepository;
import com.yura.resthw.dto.OrderDto;
import com.yura.resthw.entity.OrderEntity;
import com.yura.resthw.entity.UserEntity;
import com.yura.resthw.service.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    private static final OrderEntity ORDER_ENTITY = getOrderEntity();
    private static final OrderDto ORDER_DTO = getOrderDto();
    private static final Integer ID = 1;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void add_ShouldAddOrder() {
        when(orderRepository.save(ORDER_ENTITY)).thenReturn(ORDER_ENTITY);
        when(orderMapper.mapDtoToEntity(ORDER_DTO)).thenReturn(ORDER_ENTITY);
        when(orderMapper.mapEntityToDto(ORDER_ENTITY)).thenReturn(ORDER_DTO);

        orderService.add(ORDER_DTO);

        verify(orderRepository).save(ORDER_ENTITY);
    }

    @Test
    void findById_ShouldFindOrder() {
        when(orderRepository.findById(ID)).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderMapper.mapEntityToDto(ORDER_ENTITY)).thenReturn(ORDER_DTO);

        OrderDto actual = orderService.findById(ID);

        assertEquals(ORDER_DTO, actual);
    }

    @Test
    void findById_ShouldThrowEntityNotFoundException() {
        when(orderRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.findById(ID));
    }

    @Test
    void update_ShouldUpdateOrder() {
        when(orderRepository.findById(ID)).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderMapper.mapDtoToEntity(ORDER_DTO)).thenReturn(ORDER_ENTITY);

        orderService.update(ORDER_DTO, ID);

        verify(orderRepository).save(ORDER_ENTITY);
    }

    @Test
    void delete_ShouldDeleteOrder() {
        orderService.delete(ID);

        verify(orderRepository).deleteById(ID);
    }

    @Test
    void findAll_ShouldReturnListOfOrders() {
        List<OrderEntity> orders = Collections.singletonList(ORDER_ENTITY);
        List<OrderDto> expected = Collections.singletonList(ORDER_DTO);

        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.mapEntityToDto(ORDER_ENTITY)).thenReturn(ORDER_DTO);

        List<OrderDto> actual = orderService.findAll();

        assertEquals(expected, actual);
    }

    private static OrderEntity getOrderEntity() {
        return OrderEntity.builder()
                .withId(1)
                .withUser(UserEntity.builder().withId(1).build())
                .withPrice(10.8)
                .build();
    }

    private static OrderDto getOrderDto() {
        return OrderDto.builder()
                .withId(1)
                .withUser(1)
                .withPrice(10.8)
                .build();
    }
}
