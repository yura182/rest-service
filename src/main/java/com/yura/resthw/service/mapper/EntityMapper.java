package com.yura.resthw.service.mapper;

public interface EntityMapper<E, D> {

    D mapEntityToDto(E entity);

    E mapDtoToEntity(D dto);
}
