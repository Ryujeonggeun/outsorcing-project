package com.sparta.outsorcingproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.outsorcingproject.dto.OrdereMenuResponseDto;
import com.sparta.outsorcingproject.service.OrdersMenuService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrdersMenuController {

	private final OrdersMenuService ordersMenuService;

	@PostMapping("/{ordersId}/{menuId}/{quantity}")
	public ResponseEntity<OrdereMenuResponseDto> createOrders(
		@PathVariable long ordersId,
		@PathVariable long menuId,
		@PathVariable long quantity
	) {
		OrdereMenuResponseDto responseDto = ordersMenuService.createOrders(ordersId, menuId, quantity);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

}
