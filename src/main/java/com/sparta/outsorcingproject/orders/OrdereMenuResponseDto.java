package com.sparta.outsorcingproject.orders;

import lombok.Getter;

@Getter
public class OrdereMenuResponseDto {

	private long ordersId;
	private long quantity;

	public OrdereMenuResponseDto(OrdersMenu ordersMenu) {
		this.ordersId = ordersMenu.getOrders().getId();
		this.quantity = ordersMenu.getQuantity();
	}
}
