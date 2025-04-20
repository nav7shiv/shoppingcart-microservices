package com.navin.common;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrdersDto {

	private String userId;
	private LocalDateTime orderDate;
	private String status;
	private List<OrderItemDto> orderItems;
	
}