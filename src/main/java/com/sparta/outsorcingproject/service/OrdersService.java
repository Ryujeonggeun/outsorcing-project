package com.sparta.outsorcingproject.service;

import org.springframework.stereotype.Service;

import com.sparta.outsorcingproject.repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersService {

	private final OrdersRepository ordersRepository;

}
