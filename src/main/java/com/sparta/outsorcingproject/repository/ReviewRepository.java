package com.sparta.outsorcingproject.repository;

import com.sparta.outsorcingproject.entity.Orders;
import com.sparta.outsorcingproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByOrders(Orders orders);
}
