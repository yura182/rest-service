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
@RequestMapping("/orders")
public class OrderController {

    private final static String ID_PATH = "/{orderId}";

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(ID_PATH)
    public OrderDto getOrder(@PathVariable Integer orderId) {
        return orderService.findById(orderId);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAll();
    }

    @PostMapping
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        return orderService.add(orderDto);
    }

    @PutMapping(ID_PATH)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto, @PathVariable Integer orderId) {
        return orderService.update(orderDto, orderId);
    }

    @DeleteMapping(ID_PATH)
    public void deleteOrder(@PathVariable Integer orderId) {
        orderService.delete(orderId);
    }
}
