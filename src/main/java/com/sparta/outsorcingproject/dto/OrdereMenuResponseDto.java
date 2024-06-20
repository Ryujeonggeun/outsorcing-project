package com.sparta.outsorcingproject.dto;

import com.sparta.outsorcingproject.entity.OrdersMenu;

import lombok.Getter;

@Getter
public class OrdereMenuResponseDto {

	private long ordersId;
	private long menuId;
	private long quantity;

	public OrdereMenuResponseDto(OrdersMenu ordersMenu) {
		this.ordersId = ordersMenu.getOrders().getId();
		this.menuId = ordersMenu.getMenu().getId();
		this.quantity = ordersMenu.getQuantity();
	}
}
