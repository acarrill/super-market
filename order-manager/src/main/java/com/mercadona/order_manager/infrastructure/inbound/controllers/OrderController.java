package com.mercadona.order_manager.infrastructure.inbound.controllers;

import com.mercadona.order_manager.application.create.CreateOrderUseCase;
import com.mercadona.order_manager.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    /**
     * Endpoint for creating a new order.
     *
     * @param order the order details provided in the request body
     * @return a ResponseEntity containing the created order and HTTP status
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = createOrderUseCase.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
