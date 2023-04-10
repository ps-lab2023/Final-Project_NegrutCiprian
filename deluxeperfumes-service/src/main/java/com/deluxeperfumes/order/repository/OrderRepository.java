package com.deluxeperfumes.order.repository;

import com.deluxeperfumes.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderByExternalId(UUID orderExternalId);

    void deleteOrderByExternalId(UUID orderExternalId);

    Page<Order> findAllByIdentifierContainingIgnoreCaseOrUsernameContainingIgnoreCase(
            String searchString, String searchString1, Pageable pageable);

    Optional<Order> findOrderByIdentifier(String identifier);

}
