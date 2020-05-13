package com.yura.resthw.service.mapper;

import com.yura.resthw.dto.UserDto;
import com.yura.resthw.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserMapper implements EntityMapper<UserEntity, UserDto> {

    private final OrderMapper orderMapper;

    @Autowired
    public UserMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public UserDto mapEntityToDto(UserEntity entity) {
        return Objects.isNull(entity) ? null : UserDto.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withEmail(entity.getEmail())
                .withOrders(Optional.ofNullable(entity.getOrders())
                        .map(Collection::stream)
                        .orElseGet(Stream::empty)
                        .map(orderMapper::mapEntityToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public UserEntity mapDtoToEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : UserEntity.builder()
                .withId(dto.getId())
                .withName(dto.getName())
                .withEmail(dto.getEmail())
                .withOrders(Optional.ofNullable(dto.getOrders())
                        .map(Collection::stream)
                        .orElseGet(Stream::empty)
                        .map(orderMapper::mapDtoToEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
