package com.yura.resthw.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDto {

    private Integer id;
    private Integer userId;
    private Double price;
    private LocalDateTime dateTime;

    public OrderDto() {
    }

    public OrderDto(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.price = builder.price;
        this.dateTime = builder.dateTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
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
        OrderDto that = (OrderDto) obj;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(price, that.price) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, price, dateTime);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", user_id=" + userId +
                ", price=" + price +
                ", dateTime=" + dateTime +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private Integer userId;
        private Double price;
        private LocalDateTime dateTime;

        private Builder() {

        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withUser(Integer userId) {
            this.userId = userId;
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

        public OrderDto build() {
            return new OrderDto(this);
        }
    }

}
