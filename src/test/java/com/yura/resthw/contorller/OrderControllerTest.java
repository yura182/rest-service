package com.yura.resthw.contorller;

import com.yura.resthw.dto.OrderDto;
import com.yura.resthw.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    private static final OrderDto ORDER_DTO = getOrderDto();
    private static final Integer ID = 1;

    @Mock
    private OrderServiceImpl orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void getOrder_ShouldReturnOrder() {
        when(orderService.findById(ID)).thenReturn(ORDER_DTO);

        OrderDto actual = orderController.getOrder(ID);

        assertEquals(ORDER_DTO, actual);
    }

    @Test
    void addOrder_ShouldAddOrder() {
        when(orderService.add(ORDER_DTO)).thenReturn(ORDER_DTO);

        OrderDto actual = orderController.addOrder(ORDER_DTO);

        assertEquals(ORDER_DTO, actual);
    }

    @Test
    void updateOrder_ShouldUpdateOrder() {
        when(orderService.update(ORDER_DTO, ID)).thenReturn(ORDER_DTO);

        OrderDto actual = orderController.updateOrder(ORDER_DTO, ID);

        assertEquals(ORDER_DTO, actual);
    }

    @Test
    void deleteOrder_ShouldDeleteOrder() {
        orderController.deleteOrder(ID);

        verify(orderService).delete(ID);
    }

    private static OrderDto getOrderDto() {
        return OrderDto.builder()
                .withId(1)
                .withUser(1)
                .withPrice(10.8)
                .build();
    }
}