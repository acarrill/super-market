package com.mercadona.order_manager.domain.model;

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
public class Order {
    private UUID id;

    private String customerId;
    private List<ProductOrdered> products;
    private BigDecimal total;
    private String status;
    private OffsetDateTime creationDate;
    private OffsetDateTime completionDate;

}
