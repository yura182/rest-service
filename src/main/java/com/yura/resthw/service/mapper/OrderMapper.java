package com.yura.resthw.service.mapper;

import com.yura.resthw.dto.OrderDto;
import com.yura.resthw.entity.OrderEntity;
import com.yura.resthw.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderMapper implements EntityMapper<OrderEntity, OrderDto> {

    @Override
    public OrderDto mapEntityToDto(OrderEntity entity) {
        return Objects.isNull(entity) ? null : OrderDto.builder()
                .withId(entity.getId())
                .withUser(entity.getUserEntity().getId())
                .withPrice(entity.getPrice())
                .withDateTime(entity.getDateTime())
                .build();
    }

    @Override
    public OrderEntity mapDtoToEntity(OrderDto dto) {
        return Objects.isNull(dto) ? null : OrderEntity.builder()
                .withId(dto.getId())
                .withUser(UserEntity.builder().withId(dto.getUserId()).build())
                .withPrice(dto.getPrice())
                .withDateTime(dto.getDateTime())
                .build();
    }
}
