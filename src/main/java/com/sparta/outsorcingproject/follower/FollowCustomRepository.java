package com.sparta.outsorcingproject.follower;

import com.sparta.outsorcingproject.orders.OrdersResponseDto;
import com.sparta.outsorcingproject.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowCustomRepository {

    Page<OrdersResponseDto> findFollowedOrdersByUser(User user, Pageable pageable);

}
