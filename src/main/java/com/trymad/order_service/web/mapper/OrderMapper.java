package com.trymad.order_service.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.trymad.order_service.entity.Order;
import com.trymad.order_service.entity.OrderProduct;
import com.trymad.order_service.web.dto.OrderDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    @Mapping(source = "status.name", target = "status") 
    @Mapping(source = "items", target = "productsId", qualifiedByName = "mapProductsToIds")
    OrderDTO toDto(Order order);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true) 
    @Mapping(target = "items", ignore = true)
    Order toEntity(OrderDTO orderDTO);

    @Named("mapProductsToIds")
    default List<Long> mapProductsToIds(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                .map(orderProduct -> orderProduct.getProductId()) 
                .toList();
    }

}
