package com.sparta.outsorcingproject.dto;

import java.util.List;

import com.sparta.outsorcingproject.entity.Orders;
import com.sparta.outsorcingproject.entity.OrdersMenu;

public class OrdersResponseDto {
	private long id;

	private long user_id;

	private long store_id;

	private List<OrdersMenu> ordersMenuList;

	private long totalPrice;

	public OrdersResponseDto(Orders savedOrders) {
		this.id = savedOrders.getId();
		this.user_id = savedOrders.getUser().getId();
		this.store_id = savedOrders.getStore().getId();
		this.ordersMenuList = savedOrders.getOrdersMenu();
		this.totalPrice = savedOrders.getTotalPrice();
	}
}
