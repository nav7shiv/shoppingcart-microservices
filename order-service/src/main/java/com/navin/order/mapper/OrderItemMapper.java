package com.navin.order.mapper;
import org.mapstruct.Mapper;

import com.navin.common.OrderItemDto;
import com.navin.order.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
	
	OrderItem toOrderItem(OrderItemDto orderItemDto);
	OrderItemDto toOrderItemDto(OrderItem orderItem);

}
