package com.trymad.order_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trymad.order_service.entity.OrderStatusEntity;
import com.trymad.order_service.model.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Long> {
	
	Optional<OrderStatusEntity> findByName(OrderStatus name);

}
