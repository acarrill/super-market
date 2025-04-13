package com.mercadona.order_manager.domain.repository;

import com.mercadona.order_manager.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();

    void delete(Order order);
}
