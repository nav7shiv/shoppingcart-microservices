package com.navin.common;

import lombok.Data;

@Data
public class OrderItemDto {

	private Long productId;
	private int quantity;
	private double price;

}
