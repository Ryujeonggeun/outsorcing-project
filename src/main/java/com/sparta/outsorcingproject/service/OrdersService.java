package com.sparta.outsorcingproject.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.outsorcingproject.dto.OrdersMenuRequestDto;
import com.sparta.outsorcingproject.dto.OrdersRequestDto;
import com.sparta.outsorcingproject.dto.OrdersResponseDto;
import com.sparta.outsorcingproject.entity.Menu;
import com.sparta.outsorcingproject.entity.Orders;
import com.sparta.outsorcingproject.entity.OrdersMenu;
import com.sparta.outsorcingproject.entity.Store;
import com.sparta.outsorcingproject.entity.User;
import com.sparta.outsorcingproject.repository.MenuRepository;
import com.sparta.outsorcingproject.repository.OrdersMenuRepository;
import com.sparta.outsorcingproject.repository.OrdersRepository;
import com.sparta.outsorcingproject.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersService {

	private final StoreRepository storeRepository;
	private final MessageSource messageSource;
	private final OrdersRepository ordersRepository;
	private final OrdersMenuRepository ordersMenuRepository;
	private final MenuRepository menuRepository;

	@Transactional
	public OrdersResponseDto createOrders(User user, long storeId, OrdersRequestDto requestDto) {

		Store store = storeRepository.findById(storeId).orElseThrow(
			() -> new IllegalStateException(
				messageSource.getMessage("not.found.store", null, Locale.getDefault())
			)
		);

		Orders orders = new Orders(user, store);
		Orders savedOrders = ordersRepository.save(orders);

		long totalPrice = 0;

		for (OrdersMenuRequestDto ordersMenuDto : requestDto.getOrdersMenuList()) {
			Menu menu = menuRepository.findById(ordersMenuDto.getMenuId()).orElseThrow(
				() -> new IllegalStateException(
					messageSource.getMessage("not.found.menu", null, Locale.getDefault())
				)
			);

			totalPrice += menu.getPrice()*ordersMenuDto.getQuantity();

			OrdersMenu ordersMenu = ordersMenuRepository.save(
				new OrdersMenu(ordersMenuDto, savedOrders, menu, totalPrice));
			savedOrders.setTotalPrice(totalPrice);
			savedOrders.addOrdersMenu(ordersMenu);
		}


		return new OrdersResponseDto(savedOrders);
	}
}
