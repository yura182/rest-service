package com.yura.resthw.dao;

import com.yura.resthw.entity.OrderEntity;
import com.yura.resthw.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Optional<OrderEntity> findByUserEntityAndId(UserEntity userEntity, Integer orderId);

    Page<OrderEntity> findAllByUserEntity(UserEntity userEntity, Pageable pageable);

    void deleteByUserEntityAndId(UserEntity userEntity, Integer orderId);
}
