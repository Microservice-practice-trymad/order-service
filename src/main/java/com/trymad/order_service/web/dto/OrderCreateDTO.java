package com.trymad.order_service.web.dto;

import java.util.List;
import java.util.UUID;

public record OrderCreateDTO(UUID userId, List<ProductListDTO> productList) {
	
}
