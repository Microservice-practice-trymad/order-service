package com.trymad.order_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.order_service.entity.OrderStatusEntity;
import com.trymad.order_service.model.OrderStatus;
import com.trymad.order_service.repository.OrderStatusRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
@Transactional
public class OrderStatusService {
	
	private final OrderStatusRepository statusRepository;

	@Transactional(readOnly = true)
	OrderStatusEntity get(OrderStatus status) {
		return statusRepository.findByName(status).orElseThrow(
			() -> new EntityNotFoundException("Status " + status + " not found")
		);
	}

}
