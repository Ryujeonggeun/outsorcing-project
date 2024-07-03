package com.sparta.outsorcingproject.orders;

import java.util.List;

import lombok.Getter;

@Getter
public class OrdersRequestDto {
	List<OrdersMenuRequestDto> ordersMenuList;
}
