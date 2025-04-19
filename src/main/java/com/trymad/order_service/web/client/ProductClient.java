package com.trymad.order_service.web.client;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.trymad.order_service.web.dto.ProductDTO;
import com.trymad.order_service.web.dto.ProductListDTO;

@FeignClient(
    name = "${service.productService.name}",
	url = "${service.productService.protocol}://${service.productService.name}:${service.productService.port}"
)
public interface ProductClient {
	
	@GetMapping
	List<ProductDTO> getAll(@RequestParam List<Long> ids);

	@GetMapping("${service.productService.apiPath}/{id}")
	ProductDTO getById(@PathVariable Long id);

	@PutMapping("${service.productService.apiPath}/change-quantity")
	List<ProductDTO> changeProductQuantity(@RequestBody Set<ProductListDTO> products);

}
