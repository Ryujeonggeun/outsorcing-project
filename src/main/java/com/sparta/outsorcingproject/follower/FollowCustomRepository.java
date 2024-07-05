package com.sparta.outsorcingproject.follower;

import com.sparta.outsorcingproject.orders.OrdersResponseDto;
import com.sparta.outsorcingproject.profile.ProfileResponseDto;
import com.sparta.outsorcingproject.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowCustomRepository {

    Page<OrdersResponseDto> findFollowedOrdersByUser(User user, Pageable pageable);
    List<ProfileResponseDto> findTop10FollowedUsersProfile();

}
