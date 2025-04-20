package com.navin.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.navin.common.OrdersDto;
import com.navin.common.ProductDto;
import com.navin.order.model.Orders;
import com.navin.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrdersDto> placeOrder(@RequestBody Orders order) {
		OrdersDto placedOrderDto = orderService.placeOrder(order);
		return ResponseEntity.ok(placedOrderDto);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<OrdersDto>> getOrderByUser(@PathVariable String userId) {
		List<OrdersDto> ordersDtoList = orderService.getOrderByUser(userId);
		return ResponseEntity.ok(ordersDtoList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
		Optional<Orders> order = orderService.getOrderById(id);
		if (order.isPresent())
			return ResponseEntity.ok(order.get());
		else 
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/order-product-details/{id}")
	public ResponseEntity<List<ProductDto>> getProductsByOrderId(@PathVariable Long id) {
		ResponseEntity<Orders> ordersResponseEntity = getOrderById(id);
		Orders order = ordersResponseEntity.getBody();
		if (order == null) {
			return ResponseEntity.notFound().build();
		}
		List<ProductDto> productDtos = new ArrayList<>();
		order.getOrderItems().forEach(item -> {
			Long productId = item.getProductId();
			ResponseEntity<ProductDto> productDtoResponseEntity = orderService.getProductDetails(productId);
			if (productDtoResponseEntity.getStatusCode() == HttpStatus.OK) {
				productDtos.add(productDtoResponseEntity.getBody());
			} else if (productDtoResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
				productDtos.add(null);
			}
		});
		return ResponseEntity.ok(productDtos);
	}
	

}
