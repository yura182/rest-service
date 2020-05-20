package com.yura.resthw.contorller;

import com.yura.resthw.dto.OrderDto;
import com.yura.resthw.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

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
        when(orderService.findByUserIdAndOrderId(ID, ID)).thenReturn(ORDER_DTO);

        OrderDto actual = orderController.getOrder(ID, ID).getBody();

        assertEquals(ORDER_DTO, actual);
    }

    @Test
    void addOrder_ShouldAddOrder() {
        when(orderService.add(ID, ORDER_DTO)).thenReturn(ORDER_DTO);

        OrderDto actual = orderController.addOrder(ID, ORDER_DTO).getBody();

        assertEquals(ORDER_DTO, actual);
    }

    @Test
    void updateOrder_ShouldUpdateOrder() {
        when(orderService.update(ORDER_DTO, ID, ID)).thenReturn(ORDER_DTO);

        OrderDto actual = orderController.updateOrder(ORDER_DTO, ID, ID).getBody();

        assertEquals(ORDER_DTO, actual);
    }

    @Test
    void deleteOrder_ShouldDeleteOrder() {
        orderController.deleteOrder(ID, ID);

        verify(orderService).delete(ID, ID);
    }

    @Test
    void getAllOrders_ShouldReturnListOfOrders() {
        List<OrderDto> expected = Collections.singletonList(ORDER_DTO);

        when(orderService.findAllByUserId(ID)).thenReturn(expected);

        List<OrderDto> actual = orderController.getAllUserOrders(ID).getBody();

        assertEquals(expected, actual);
    }

    private static OrderDto getOrderDto() {
        return OrderDto.builder()
                .withId(1)
                .withUser(1)
                .withPrice(10.8)
                .build();
    }
}
