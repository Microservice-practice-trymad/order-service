package com.trymad.order_service.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.order_service.entity.Order;
import com.trymad.order_service.entity.OrderProduct;
import com.trymad.order_service.model.OrderStatus;
import com.trymad.order_service.repository.OrderRepository;
import com.trymad.order_service.web.client.ProductClient;
import com.trymad.order_service.web.client.UserClient;
import com.trymad.order_service.web.dto.OrderCreateDTO;
import com.trymad.order_service.web.dto.ProductDTO;
import com.trymad.order_service.web.dto.ProductListDTO;
import com.trymad.order_service.web.mapper.OrderProductMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
@Transactional
public class OrderService {
	
	@Lazy private final OrderService orderService;
	private final OrderStatusService statusService;

	private final OrderRepository orderRepository;

	private final ProductClient productClient;
	private final UserClient userClient;

	private final OrderProductMapper orderProductMapper;

	public Order getById(Long id) {
		return orderRepository.findById(id).orElseThrow(
			() -> new EntityNotFoundException("Order with id " + id + " not found")
		);
	}

	public Order createOrder(OrderCreateDTO createDto) {
		userClient.getById(createDto.userId()); // check if exists
		if(createDto.productList().isEmpty()) {
			throw new IllegalArgumentException("Product list in order is empty");
		}

		final Set<ProductListDTO> decreaseQuantitySet = createDto.productList().stream()
			.map(productList -> {
				final ProductListDTO dto = new ProductListDTO(productList.id(), productList.count() * -1);
				return dto;
			}).collect(Collectors.toSet());

		if(decreaseQuantitySet.size() != createDto.productList().size()) {
			throw new IllegalArgumentException("In list exists equals product id");
		}

		final List<ProductDTO> products = productClient.changeProductQuantity(decreaseQuantitySet);
		final List<OrderProduct> orderProducts = orderProductMapper.toEntity(products);

		final Order order = new Order();
		orderProducts.forEach(orderProduct -> orderProduct.setOrder(order));
		order.setItems(orderProducts);
		order.setUserId(createDto.userId());
		order.setStatus(statusService.get(OrderStatus.IN_PROGRESS));

		return orderRepository.save(order);
	}
}
 