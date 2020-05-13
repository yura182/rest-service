package com.yura.resthw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private Double price;

    private LocalDateTime dateTime;

    public OrderEntity() {
    }

    public OrderEntity(Builder builder) {
        this.id = builder.id;
        this.userEntity = builder.userEntity;
        this.price = builder.price;
        this.dateTime = builder.dateTime;
    }

    public Integer getId() {
        return id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrderEntity that = (OrderEntity) obj;
        return Objects.equals(id, that.id) &&
                Objects.equals(userEntity, that.userEntity) &&
                Objects.equals(price, that.price) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userEntity, price, dateTime);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", userEntity=" + userEntity +
                ", price=" + price +
                ", dateTime=" + dateTime +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private UserEntity userEntity;
        private Double price;
        private LocalDateTime dateTime;

        private Builder() {

        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withUser(UserEntity userEntity) {
            this.userEntity = userEntity;
            return this;
        }

        public Builder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder withDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public OrderEntity build() {
            return new OrderEntity(this);
        }
    }
}
