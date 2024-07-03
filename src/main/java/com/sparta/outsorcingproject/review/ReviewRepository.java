package com.sparta.outsorcingproject.review;

import com.sparta.outsorcingproject.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByOrders(Orders orders);

    List<Review> findAllByStore_Id(Long storeId);
}
