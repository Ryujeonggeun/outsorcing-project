package com.sparta.outsorcingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.outsorcingproject.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
