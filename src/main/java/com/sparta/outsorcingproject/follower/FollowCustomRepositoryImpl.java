package com.sparta.outsorcingproject.follower;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.outsorcingproject.orders.Orders;
import com.sparta.outsorcingproject.orders.OrdersResponseDto;
import com.sparta.outsorcingproject.orders.QOrders;
import com.sparta.outsorcingproject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public class FollowCustomRepositoryImpl implements FollowCustomRepository{

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    MessageSource messageSource;

    @Override
    public Page<OrdersResponseDto> findFollowedOrdersByUser(User user, Pageable pageable) {
        QOrders qOrders = QOrders.orders;
        QFollower qFollower = QFollower.follower1;

        //팔로우한 유저 ID 목록 조회
        List<Long> followedUserIds = jpaQueryFactory
                .select(qFollower.follower.id)
                .from(qFollower)
                .where(qFollower.follower.eq(user))
                .fetch();

        if (followedUserIds.isEmpty()) {
            throw new IllegalArgumentException(messageSource
                    .getMessage("not.find.follower",null, Locale.getDefault()));
        }

        //팔로우한 유저들의 주문들 조회
        List<Orders> ordersList = jpaQueryFactory
                .selectFrom(qOrders)
                .where(qOrders.user.id.in(followedUserIds))
                .orderBy(qOrders.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //총 주문 수 조회
        long total = jpaQueryFactory
                .selectFrom(qOrders)
                .where(qOrders.user.id.in(followedUserIds))
                .fetchCount();

        // Orders -> OrdersResponseDto
        List<OrdersResponseDto> ordersResponseDtoList = ordersList.stream()
                .map(OrdersResponseDto::new)
                .toList();

        return new PageImpl<>(ordersResponseDtoList,pageable,total);
    }
}
