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

@RestController
@RequestMapping("order")
public class OrderController {

    private final static String ID_PATH = "/{id}";

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(ID_PATH)
    public OrderDto getOrder(@PathVariable Integer id) {
        return orderService.findById(id);
    }

    @PostMapping
    public OrderDto addOrder(@RequestBody OrderDto orderDto) {
        return orderService.add(orderDto);
    }

    @PutMapping(ID_PATH)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto, @PathVariable Integer id) {
        return orderService.update(orderDto, id);
    }

    @DeleteMapping(ID_PATH)
    public void deleteOrder(@PathVariable Integer id) {
        orderService.delete(id);
    }
}
