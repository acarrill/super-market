package com.mercadona.order_manager.infrastructure.outbound.persistence.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadona.order_manager.domain.model.Order;
import com.mercadona.order_manager.domain.model.ProductOrdered;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Id
    @Column(length = 36, nullable = false)
    private UUID id;

    private String customerId;
    @Column(columnDefinition = "TEXT")
    private String products;
    private BigDecimal total;
    private String status;
    private OffsetDateTime creationDate;
    private OffsetDateTime completionDate;

    public static OrderEntity fromDomain(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setCustomerId(order.getCustomerId());
        entity.setProducts(convertProductsToJson(order.getProducts()));
        entity.setStatus(order.getStatus());
        entity.setCreationDate(order.getCreationDate());
        entity.setCompletionDate(order.getCompletionDate());
        return entity;
    }

    public Order toDomain() {
        return new Order(
                this.id,
                this.customerId,
                convertProductsFromJson(this.products),
                this.total,
                this.status,
                this.creationDate,
                this.completionDate
        );
    }

    private static String convertProductsToJson(List<ProductOrdered> products) {
        try {
            return objectMapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert products list to JSON", e);
        }
    }

    private static List<ProductOrdered> convertProductsFromJson(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<ProductOrdered>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON to products list", e);
        }
    }
}
