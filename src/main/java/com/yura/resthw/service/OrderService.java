package com.yura.resthw.service;

import com.yura.resthw.dto.OrderDto;

public interface OrderService {

    OrderDto add(OrderDto orderDto);

    OrderDto findById(Integer id);

    OrderDto update(OrderDto orderDto, Integer id);

    void delete(Integer id);
}
