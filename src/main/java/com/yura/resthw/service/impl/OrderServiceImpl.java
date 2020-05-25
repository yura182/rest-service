package com.yura.resthw.service.impl;

import com.yura.resthw.dao.OrderRepository;
import com.yura.resthw.dto.OrderDto;
import com.yura.resthw.entity.OrderEntity;
import com.yura.resthw.entity.UserEntity;
import com.yura.resthw.service.OrderService;
import com.yura.resthw.service.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final EntityMapper<OrderEntity, OrderDto> orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EntityMapper<OrderEntity, OrderDto> orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto add(Integer userId, OrderDto orderDto) {
        orderDto.setDateTime(LocalDateTime.now());
        orderDto.setUserId(userId);

        return orderMapper.mapEntityToDto(saveEntity(orderDto));
    }

    @Override
    public OrderDto findByUserIdAndOrderId(Integer userId, Integer orderId) {
        return orderMapper.mapEntityToDto(getOrderByUserIdAndOrderId(userId, orderId));
    }

    @Override
    public Page<OrderDto> findAllByUserId(Integer userId, Pageable pageable) {
        return orderRepository
                .findAllByUserEntity(getUserEntityWithId(userId), pageable)
                .map(orderMapper::mapEntityToDto);
    }

    @Override
    public OrderDto update(OrderDto orderDto, Integer userId, Integer orderId) {
        orderRepository
                .findByUserEntityAndId(getUserEntityWithId(userId), orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        orderDto.setId(orderId);
        orderDto.setUserId(userId);
        orderDto.setDateTime(LocalDateTime.now());

        return orderMapper.mapEntityToDto(saveEntity(orderDto));
    }

    @Override
    @Transactional
    public void delete(Integer userId, Integer orderId) {
        orderRepository.deleteByUserEntityAndId(getUserEntityWithId(userId), orderId);
    }

    private UserEntity getUserEntityWithId(Integer userId) {
        return UserEntity.builder().withId(userId).build();
    }

    private OrderEntity saveEntity(OrderDto orderDto) {
        return orderRepository.save(orderMapper.mapDtoToEntity(orderDto));
    }

    private OrderEntity getOrderByUserIdAndOrderId(Integer userId, Integer orderId) {
        return orderRepository
                .findByUserEntityAndId(getUserEntityWithId(userId), orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }
}
