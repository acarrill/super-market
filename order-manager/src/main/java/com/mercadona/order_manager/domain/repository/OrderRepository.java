package com.mercadona.order_manager.domain.repository;

import com.mercadona.order_manager.domain.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    void delete(Order order);
}
