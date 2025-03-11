package com.trymad.order_service.web.dto;

public record ProductDTO(
	Long id, 

	String name, 

	Integer price,

	Integer count) {
	
}
