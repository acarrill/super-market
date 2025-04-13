package com.mercadona.order_manager.infrastructure.inbound.kafka.model;

import com.mercadona.order_manager.domain.model.ProductOrdered;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class OrderMessage {
    private UUID orderId;
    private String customerId;
    private String status;
    private BigDecimal total;
    private OffsetDateTime creationDate;
    private List<ProductOrdered> products;

}
