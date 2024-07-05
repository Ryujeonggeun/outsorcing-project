package com.sparta.outsorcingproject.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.outsorcingproject.menu.Menu;
import com.sparta.outsorcingproject.store.Store;
import com.sparta.outsorcingproject.user.User;
import com.sparta.outsorcingproject.follower.FollowRepository;
import com.sparta.outsorcingproject.menu.MenuRepository;
import com.sparta.outsorcingproject.store.StoreRepository;
import com.sparta.outsorcingproject.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersService {

	private final StoreRepository storeRepository;
	private final MessageSource messageSource;
	private final OrdersRepository ordersRepository;
	private final OrdersMenuRepository ordersMenuRepository;
	private final MenuRepository menuRepository;
	private final FollowRepository followRepository;
	private final UserRepository userRepository;

	@Transactional
	public OrdersResponseDto createOrders(User user, long storeId, OrdersRequestDto requestDto) {

		Store store = storeRepository.findById(storeId).orElseThrow(
			() -> new IllegalArgumentException(
				messageSource.getMessage("not.find.store", null, Locale.getDefault())
			)
		);

		Orders orders = new Orders(user, store);
		Orders savedOrders = ordersRepository.save(orders);

		ordersMenuConvert(requestDto, savedOrders);

		return new OrdersResponseDto(savedOrders);
	}

	@Transactional
	public OrdersResponseDto editOrders(User user, long ordersId, OrdersRequestDto requestDto) {
		Orders orders = ordersRepository.findById(ordersId).orElseThrow(
			() -> new IllegalArgumentException(
				messageSource.getMessage("not.find.orders", null, Locale.getDefault())
			)
		);

		if (!orders.getUser().getId().equals(user.getId())) {
			throw new IllegalArgumentException(
				messageSource.getMessage("mismatch.user", null, Locale.getDefault())
			);
		}

		orders.getOrdersMenu().clear();

		ordersMenuConvert(requestDto, orders);

		return new OrdersResponseDto(orders);
	}

	public List<OrdersResponseDto> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);

		Page<Orders> orders = ordersRepository.findAllByOrderByCreatedAtDesc(pageRequest);
		List<OrdersResponseDto> responseDtoList = new ArrayList<>();

		for (Orders order : orders) {
			responseDtoList.add(new OrdersResponseDto(order));
		}

		return responseDtoList;
	}

	public OrdersResponseDto find(long ordersId) {

		Orders orders = ordersRepository.findById(ordersId).orElseThrow(
			() -> new IllegalArgumentException(
				messageSource.getMessage("not.find.orders", null, Locale.getDefault())
			)
		);

		return new OrdersResponseDto(orders);
	}

	public void delete(long ordersId, User user) {
		Orders orders = ordersRepository.findById(ordersId).orElseThrow(
			() -> new IllegalArgumentException(
				messageSource.getMessage("not.find.orders", null, Locale.getDefault())
			)
		);

		if (!orders.getUser().getId().equals(user.getId())) {
			throw new IllegalArgumentException(
				messageSource.getMessage("mismatch.user", null, Locale.getDefault())
			);
		}

		ordersRepository.deleteById(ordersId);
	}

	private void ordersMenuConvert(OrdersRequestDto requestDto, Orders savedOrders) {
		long totalPrice = 0;

		for (OrdersMenuRequestDto ordersMenuDto : requestDto.getOrdersMenuList()) {
			Menu menu = menuRepository.findById(ordersMenuDto.getMenuId()).orElseThrow(
				() -> new IllegalArgumentException(
					messageSource.getMessage("not.find.menu", null, Locale.getDefault())
				)
			);

			totalPrice += menu.getPrice() * ordersMenuDto.getQuantity();

			OrdersMenu ordersMenu = ordersMenuRepository.save(
				new OrdersMenu(ordersMenuDto, savedOrders, menu));
			savedOrders.setTotalPrice(totalPrice);
			savedOrders.addOrdersMenu(ordersMenu);
		}
	}

	public Page<OrdersResponseDto> findAllByFollow(User user, long followerId, int page, int size) {
		User follower = userRepository.findById(followerId).orElseThrow(
			() -> new IllegalArgumentException(
				messageSource.getMessage("not.find.user", null, Locale.getDefault())
			)
		);

		Pageable pageable = PageRequest.of(page,size);


		return followRepository.findFollowedOrdersByUser(follower,pageable);
	}
}
