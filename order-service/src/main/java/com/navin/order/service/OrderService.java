package com.navin.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.navin.common.OrdersDto;
import com.navin.common.ProductDto;
import com.navin.order.feign.ProductClient;
import com.navin.order.mapper.OrdersMapper;
import com.navin.order.model.Orders;
import com.navin.order.model.StatusEnum;
import com.navin.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repository;
	private final ProductClient productClient;
	
	@Autowired
	private OrdersMapper orderMapper;

	public OrdersDto placeOrder(Orders order) {
		order.setOrderDate(LocalDateTime.now());
		order.setStatus(StatusEnum.PLACED.getValue());
		order.getOrderItems().forEach(item -> item.setOrder(order));
		Orders savedOrder = repository.save(order);
		return orderMapper.toOrdersDto(savedOrder);
	}

	public List<OrdersDto> getOrderByUser(String userId) {
		List<Orders> orderss = repository.findByUserId(userId);
		return orderMapper.toOrdersDtoList(orderss);
	}

	public Optional<Orders> getOrderById(Long id) {
		return repository.findById(id);
	}

	public ResponseEntity<ProductDto> getProductDetails(Long productId) {
		return productClient.getProductById(productId);
	}

}
