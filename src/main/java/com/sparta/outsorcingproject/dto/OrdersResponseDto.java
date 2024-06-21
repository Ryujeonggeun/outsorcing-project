package com.sparta.outsorcingproject.dto;

import java.util.ArrayList;
import java.util.List;

import com.sparta.outsorcingproject.entity.Orders;
import com.sparta.outsorcingproject.entity.OrdersMenu;

import lombok.Getter;

@Getter
public class OrdersResponseDto {

	private final long id;
	private final long user_id;
	private final long store_id;
	private final List<OrdersMenuResponseDto> ordersMenuList = new ArrayList<>();
	private final long totalPrice;

	public OrdersResponseDto(Orders savedOrders) {
		this.id = savedOrders.getId();
		this.user_id = savedOrders.getUser().getId();
		this.store_id = savedOrders.getStore().getId();
		this.totalPrice = savedOrders.getTotalPrice();
		for (OrdersMenu ordersMenu : savedOrders.getOrdersMenu()) {
			ordersMenuList.add(new OrdersMenuResponseDto(ordersMenu));
		}
	}
}
