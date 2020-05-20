package com.yura.resthw.contorller;

import com.yura.resthw.dto.OrderDto;
import com.yura.resthw.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users/{userId}/orders")
public class OrderController {

    private final static String ID_PATH = "/{orderId}";

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(ID_PATH)
    public OrderDto getOrder(@PathVariable Integer userId, @PathVariable Integer orderId) {
        return orderService.findByUserIdAndOrderId(userId, orderId);
    }

    @GetMapping
    public List<OrderDto> getAllUserOrders(@PathVariable Integer userId) {
        return orderService.findAllByUserId(userId);
    }

    @PostMapping
    public OrderDto addOrder(@PathVariable Integer userId, @RequestBody OrderDto orderDto) {
        return orderService.add(userId, orderDto);
    }

    @PutMapping(ID_PATH)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto,
                                @PathVariable Integer userId,
                                @PathVariable Integer orderId) {
        return orderService.update(orderDto, userId, orderId);
    }

    @DeleteMapping(ID_PATH)
    public void deleteOrder(@PathVariable Integer userId, @PathVariable Integer orderId) {
        orderService.delete(userId, orderId);
    }
}
