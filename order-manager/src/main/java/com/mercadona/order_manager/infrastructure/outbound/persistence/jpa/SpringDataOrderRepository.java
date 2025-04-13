package com.mercadona.order_manager.infrastructure.outbound.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, UUID> {
    // Additional query methods can be defined here if necessary.
}
