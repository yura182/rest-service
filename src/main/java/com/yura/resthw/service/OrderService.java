package com.yura.resthw.service;

import com.yura.resthw.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto add(OrderDto orderDto);

    OrderDto findById(Integer id);

    List<OrderDto> findAll();

    OrderDto update(OrderDto orderDto, Integer id);

    void delete(Integer id);
}
