package com.yura.resthw.contorller;

import com.yura.resthw.dto.OrderDto;
import com.yura.resthw.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer userId, @PathVariable Integer orderId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.findByUserIdAndOrderId(userId, orderId));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<OrderDto>> getAllUserOrders(@PathVariable Integer userId,
                                                           @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.findAllByUserId(userId, pageable));
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@PathVariable Integer userId, @RequestBody OrderDto orderDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.add(userId, orderDto));
    }

    @PutMapping(ID_PATH)
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto,
                                @PathVariable Integer userId,
                                @PathVariable Integer orderId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.update(orderDto, userId, orderId));
    }

    @DeleteMapping(ID_PATH)
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer userId, @PathVariable Integer orderId) {
        orderService.delete(userId, orderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
