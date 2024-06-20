package com.sparta.outsorcingproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.outsorcingproject.dto.OrdersRequestDto;
import com.sparta.outsorcingproject.entity.User;
import com.sparta.outsorcingproject.service.OrdersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

	private final String CREATE_ORDERS = "주문 생성 완료";
	private final OrdersService ordersService;

	@PostMapping("/{storeId}")
	public ResponseEntity<String> createOrders(@AuthenticationPrincipal User user,
		@PathVariable long storeId, OrdersRequestDto requestDto) {
		ordersService.createOrders(user, storeId, requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(CREATE_ORDERS);
	}
}
