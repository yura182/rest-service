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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
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

        orderService.add(ID, ORDER_DTO);

        verify(orderRepository).save(ORDER_ENTITY);
    }

    @Test
    void findById_ShouldFindOrder() {
        when(orderRepository.findByUserEntityAndId(getUserEntityWithId(ID), ID)).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderMapper.mapEntityToDto(ORDER_ENTITY)).thenReturn(ORDER_DTO);

        OrderDto actual = orderService.findByUserIdAndOrderId(ID, ID);

        assertEquals(ORDER_DTO, actual);
    }

    @Test
    void findById_ShouldThrowEntityNotFoundException() {
        when(orderRepository.findByUserEntityAndId(getUserEntityWithId(ID), ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.findByUserIdAndOrderId(ID, ID));
    }

    @Test
    void update_ShouldUpdateOrder() {
        when(orderRepository.findByUserEntityAndId(getUserEntityWithId(ID), ID)).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderMapper.mapDtoToEntity(ORDER_DTO)).thenReturn(ORDER_ENTITY);

        orderService.update(ORDER_DTO, ID, ID);

        verify(orderRepository).save(ORDER_ENTITY);
    }

    @Test
    void delete_ShouldDeleteOrder() {
        orderService.delete(ID, ID);

        verify(orderRepository).deleteByUserEntityAndId(getUserEntityWithId(ID), ID);
    }

    @Test
    void findAll_ShouldReturnListOfOrders() {
        Page<OrderEntity> orders = new PageImpl<>(Collections.singletonList(ORDER_ENTITY));
        Page<OrderDto> expected = new PageImpl<>(Collections.singletonList(ORDER_DTO));
        Pageable pageable = PageRequest.of(1, 1);

        when(orderRepository.findAllByUserEntity(getUserEntityWithId(ID), pageable)).thenReturn(orders);
        when(orderMapper.mapEntityToDto(ORDER_ENTITY)).thenReturn(ORDER_DTO);

        Page<OrderDto> actual = orderService.findAllByUserId(ID, pageable);

        assertEquals(expected, actual);
    }

    private  UserEntity getUserEntityWithId(Integer id) {
        return UserEntity.builder().withId(id).build();
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
