package com.mercadona.order_manager.infrastructure.outbound.persistence.jpa;

import com.mercadona.order_manager.domain.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal total;
    private String status;
    private LocalDateTime createdDate;

    public static OrderEntity fromDomain(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setDescription(order.getDescription());
        entity.setTotal(order.getTotal());
        entity.setStatus(order.getStatus());
        entity.setCreatedDate(order.getCreatedDate());
        return entity;
    }

    public Order toDomain() {
        return new Order(
                this.id,
                this.description,
                this.total,
                this.status,
                this.createdDate
        );
    }
}
