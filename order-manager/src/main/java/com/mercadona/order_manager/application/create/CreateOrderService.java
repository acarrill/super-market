package com.mercadona.order_manager.application.create;

import com.mercadona.order_manager.domain.model.Order;
import com.mercadona.order_manager.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        // Optional: Validate the order using domain rules.
//        if (!order.isValid()) {
//            throw new IllegalArgumentException("Invalid order details");
//        }
        // Persist the order using the command repository (outbound port)
        return orderRepository.save(order);
    }
}
