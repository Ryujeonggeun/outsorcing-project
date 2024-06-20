package com.sparta.outsorcingproject.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class OrdersRequestDto {
	List<OrdersMenuRequestDto> ordersMenuList;
}
