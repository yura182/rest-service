package com.yura.resthw.service;

import com.yura.resthw.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderDto add(Integer userId, OrderDto orderDto);

    OrderDto findByUserIdAndOrderId(Integer userId, Integer orderId);

    Page<OrderDto> findAllByUserId(Integer userId, Pageable pageable);

    OrderDto update(OrderDto orderDto, Integer userId, Integer orderId);

    void delete(Integer userId, Integer orderId);
}
