package com.sparta.outsorcingproject.review;

import com.sparta.outsorcingproject.orders.Orders;
import com.sparta.outsorcingproject.store.Store;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByOrders(Orders orders);

    /** 존재하지 않는 리뷰 **/
    default Review findReviewById(Long id, MessageSource messageSource) {
        return findById(id).orElseThrow(()->new IllegalArgumentException(messageSource.getMessage("not.find.review",null, Locale.getDefault())));
    }

    List<Review> findAllByStore_Id(Long storeId);
}
