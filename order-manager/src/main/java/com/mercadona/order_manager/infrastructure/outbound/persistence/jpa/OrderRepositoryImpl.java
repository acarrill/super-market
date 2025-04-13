package com.mercadona.order_manager.infrastructure.outbound.persistence.jpa;

import com.mercadona.order_manager.domain.model.Order;
import com.mercadona.order_manager.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final SpringDataOrderRepository springDataOrderRepository;

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = OrderEntity.fromDomain(order);
        OrderEntity savedEntity = springDataOrderRepository.save(orderEntity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return springDataOrderRepository.findById(id)
                .map(OrderEntity::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return springDataOrderRepository.findAll().stream()
                .map(OrderEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Order order) {
        OrderEntity orderEntity = OrderEntity.fromDomain(order);
        springDataOrderRepository.delete(orderEntity);
    }
}
