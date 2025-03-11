package com.trymad.order_service.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.order_service.service.OrderService;
import com.trymad.order_service.web.dto.OrderCreateDTO;
import com.trymad.order_service.web.dto.OrderDTO;
import com.trymad.order_service.web.mapper.OrderMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	
	private final OrderService orderService;
	private final OrderMapper orderMapper;

	@PostMapping
	public OrderDTO createOrder(@RequestBody OrderCreateDTO createDto) {
		return orderMapper.toDto(orderService.createOrder(createDto));
	}

}
