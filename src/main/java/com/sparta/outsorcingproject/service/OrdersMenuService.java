package com.sparta.outsorcingproject.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.sparta.outsorcingproject.dto.OrdereMenuResponseDto;
import com.sparta.outsorcingproject.entity.Menu;
import com.sparta.outsorcingproject.entity.Orders;
import com.sparta.outsorcingproject.entity.OrdersMenu;
import com.sparta.outsorcingproject.repository.MenuRepository;
import com.sparta.outsorcingproject.repository.OrdersMenuRepository;
import com.sparta.outsorcingproject.repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersMenuService {
	//
	// private final OrdersMenuRepository ordersMenuRepository;
	// private final OrdersRepository ordersRepository;
	// private final MenuRepository menuRepository;
	// private final MessageSource messageSource;
	//
	// public OrdereMenuResponseDto createOrders(long ordersId, long menuId, long quantity) {
	// 	Orders orders = ordersRepository.findById(ordersId).orElseThrow(
	// 		() -> new IllegalArgumentException(
	// 			messageSource.getMessage("not.find.orders", null, Locale.getDefault()))
	// 	);
	//
	// 	Menu menu = menuRepository.findById(menuId).orElseThrow(
	// 		() -> new IllegalArgumentException(
	// 			messageSource.getMessage("not.find.menu", null, Locale.getDefault()))
	// 	);
	//
	// 	OrdersMenu ordersMenu = new OrdersMenu(ordersMenuDto, ordersId);
	// 	return new OrdereMenuResponseDto(ordersMenu);
	// }
}
