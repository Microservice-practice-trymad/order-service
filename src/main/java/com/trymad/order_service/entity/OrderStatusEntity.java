package com.trymad.order_service.entity;

import java.util.ArrayList;
import java.util.List;

import com.trymad.order_service.model.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "order_statuses")
public class OrderStatusEntity {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
	@Enumerated(value = EnumType.STRING)
    private OrderStatus name;

	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();
	
}
