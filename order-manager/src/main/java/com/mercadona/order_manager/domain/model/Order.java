package com.mercadona.order_manager.domain.model;

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
public class Order {
    private Long id;
    private String description;
    private BigDecimal total;
    private String status;
    private LocalDateTime createdDate;

    public boolean isValid() {
        return total != null && total.compareTo(BigDecimal.ZERO) > 0;
    }

}
