package com.yura.resthw.dao;

import com.yura.resthw.entity.OrderEntity;
import com.yura.resthw.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("SELECT o FROM OrderEntity o WHERE o.id = :orderId AND o.userEntity.id = :userId")
    Optional<OrderEntity> findByUserIdAndOrderId(Integer userId, Integer orderId);

    @Query("SELECT o FROM OrderEntity o WHERE o.userEntity.id = :userId")
    List<OrderEntity> findAllByUserId(Integer userId);

    void deleteByUserEntityAndId(UserEntity userEntity, Integer orderId);
}
