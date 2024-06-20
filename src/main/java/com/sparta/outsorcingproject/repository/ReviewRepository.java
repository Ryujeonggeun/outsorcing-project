package com.sparta.outsorcingproject.repository;

import com.sparta.outsorcingproject.entity.Orders;
import com.sparta.outsorcingproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByOrders(Orders orders);

    List<Review> findByStoreId(Long storeId);
}
