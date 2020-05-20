package com.yura.resthw.service;

import com.yura.resthw.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto add(Integer userId, OrderDto orderDto);

    OrderDto findByUserIdAndOrderId(Integer userId, Integer orderId);

    List<OrderDto> findAllByUserId(Integer userId);

    OrderDto update(OrderDto orderDto, Integer userId, Integer orderId);

    void delete(Integer userId, Integer orderId);
}
