package com.sparta.outsorcingproject.dto;

import com.sparta.outsorcingproject.entity.Menu;

import lombok.Getter;

@Getter
public class OrdersMenuRequestDto {
	private long quantity;
	private long menuId;
}
