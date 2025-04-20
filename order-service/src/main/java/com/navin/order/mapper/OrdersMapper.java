package com.navin.order.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.navin.common.OrdersDto;
import com.navin.order.model.Orders;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrdersMapper {
	
	Orders toOrders(OrdersDto ordersDto);

	OrdersDto toOrdersDto(Orders orders);

	List<Orders> toOrdersList(List<OrdersDto> ordersDtod);

	List<OrdersDto> toOrdersDtoList(List<Orders> orderss);

}
