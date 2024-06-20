package com.sparta.outsorcingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.outsorcingproject.entity.OrdersMenu;

public interface OrdersMenuRepository extends JpaRepository<OrdersMenu, Long> {
}
