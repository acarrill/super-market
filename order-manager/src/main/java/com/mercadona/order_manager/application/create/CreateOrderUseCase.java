package com.mercadona.order_manager.application.create;

import com.mercadona.order_manager.domain.model.Order;

public interface CreateOrderUseCase {

    /**
     * Creates a new order.
     *
     * @param order the order details to create
     * @return the created order
     */
    Order createOrder(Order order);
}
