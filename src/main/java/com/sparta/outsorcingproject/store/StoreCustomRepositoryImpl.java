package com.sparta.outsorcingproject.store;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.outsorcingproject.like.LikeContentType;
import com.sparta.outsorcingproject.like.QLike;
import com.sparta.outsorcingproject.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoreCustomRepositoryImpl implements StoreCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public StoreCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<StoreResponseDto> findLikedStoresByUser(User user, Pageable pageable) {
        QLike qLike = QLike.like;
        QStore qStore = QStore.store;

        BooleanExpression expression = qLike.user.eq(user)
                .and(qLike.contentType.eq(LikeContentType.STORE));

        //좋아요한 상점 ID 목록 조회
        List<Long> likedStoreIds = jpaQueryFactory
                .select(qLike.contentId)
                .from(qLike)
                .where(expression)
                .fetch();

        if (likedStoreIds.isEmpty()) {
            throw new IllegalArgumentException("좋아요한 상점이 없습니다.");
        }

        // 좋아요한 상점조회
        List<Store> stores = jpaQueryFactory
                .selectFrom(qStore)
                .where(qStore.id.in(likedStoreIds))
                .orderBy(qStore.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 좋아요한 상점 수 조회
        long total = jpaQueryFactory
                .selectFrom(qStore)
                .where(qStore.id.in(likedStoreIds))
                .fetchCount();

        // Store -> StoreResponseDto
        List<StoreResponseDto> storeResponseDtoList = stores.stream()
                .map(StoreResponseDto::new)
                .toList();

        return new PageImpl<>(storeResponseDtoList,pageable,total);
    }
}
