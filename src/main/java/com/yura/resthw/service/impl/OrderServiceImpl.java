package com.yura.resthw.service.impl;

import com.yura.resthw.dao.OrderRepository;
import com.yura.resthw.dto.OrderDto;
import com.yura.resthw.entity.OrderEntity;
import com.yura.resthw.service.OrderService;
import com.yura.resthw.service.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public OrderDto add(OrderDto orderDto) {
        orderDto.setDateTime(LocalDateTime.now());

        return orderMapper.mapEntityToDto(saveEntity(orderDto));
    }

    @Override
    public OrderDto findById(Integer id) {
        return orderMapper.mapEntityToDto(getOrderById(id));
    }

    @Override
    public OrderDto update(OrderDto orderDto, Integer id) {
        orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        orderDto.setId(id);

        return orderMapper.mapEntityToDto(saveEntity(orderDto));
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

    private OrderEntity saveEntity(OrderDto orderDto) {
        return orderRepository.save(orderMapper.mapDtoToEntity(orderDto));
    }

    private OrderEntity getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }
}
