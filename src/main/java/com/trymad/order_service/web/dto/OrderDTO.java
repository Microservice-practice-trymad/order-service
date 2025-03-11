package com.trymad.order_service.web.dto;

import java.util.List;
import java.util.UUID;

import com.trymad.order_service.model.OrderStatus;

public record OrderDTO(
	UUID userId, 

	List<Long> productsId, 

	OrderStatus status) {
}
