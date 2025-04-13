package com.mercadona.order_manager.infrastructure.inbound.kafka;

import com.mercadona.order_manager.application.create.CreateOrderUseCase;
import com.mercadona.order_manager.domain.model.Order;
import com.mercadona.order_manager.domain.model.ProductOrdered;
import com.mercadona.order_manager.infrastructure.inbound.kafka.model.OrderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderKafkaConsumer {

    private final CreateOrderUseCase createOrderUseCase;

    @KafkaListener(topics = "msg.order", groupId = "order-manager-group")
    public void consumeOrderMessage(OrderMessage orderMessage) {
        try {
            log.info("Received Order Message from Kafka topic [msg.order]: {}", orderMessage);
            Order order = mapToDomain(orderMessage);
            createOrderUseCase.createOrder(order);
        } catch (Exception e) {
            log.error("Failed processing Kafka message: {}", e.getMessage(), e);
            // Opcionalmente añade lógica para enviar a un Dead Letter Queue (DLQ)
        }
    }

    private Order mapToDomain(OrderMessage msg) {
        // Validate products
        List<ProductOrdered> validProducts = msg.getProducts().stream()
                .filter(ProductOrdered::isValid)
                .collect(Collectors.toList());

        return Order.builder()
                .id(msg.getOrderId())  // let the DB generate the id
                .total(calculateOrderTotal(validProducts))
                .status(msg.getStatus())
                .creationDate(msg.getCreationDate())
                .products(validProducts)
                .build();
    }

    private BigDecimal calculateOrderTotal(List<ProductOrdered> products) {
        // TODO: real logic might involve querying a product service or database
        BigDecimal unitPrice = new BigDecimal("10.00"); // example fixed price
        return products.stream()
                .map(product -> unitPrice.multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
