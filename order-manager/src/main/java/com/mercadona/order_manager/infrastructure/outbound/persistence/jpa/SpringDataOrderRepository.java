package com.mercadona.order_manager.infrastructure.outbound.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Long> {
    // Additional query methods can be defined here if necessary.
}
