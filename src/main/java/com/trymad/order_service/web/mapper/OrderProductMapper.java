package com.trymad.order_service.web.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.trymad.order_service.entity.OrderProduct;
import com.trymad.order_service.web.dto.ProductDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderProductMapper {
	
	@Mapping(target = "productId", source = "id")
	OrderProduct toEntity(ProductDTO productDTO);

	@Mapping(target = "productId", source = "id")
	List<OrderProduct> toEntity(Collection<ProductDTO> productDTO);

}
