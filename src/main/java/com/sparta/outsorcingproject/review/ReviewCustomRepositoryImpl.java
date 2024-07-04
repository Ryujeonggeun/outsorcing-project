package com.sparta.outsorcingproject.review;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.outsorcingproject.like.LikeContentType;
import com.sparta.outsorcingproject.like.QLike;
import com.sparta.outsorcingproject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository{

    @Autowired
    JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<ReviewResponseDto> findLikedReviewByUser(User user, Pageable pageable) {
        QLike qLike = QLike.like;
        QReview qReview = QReview.review1;

        BooleanExpression expression = qLike.user.eq(user)
                .and(qLike.contentType.eq(LikeContentType.REVIEW));

        //좋아요한 리뷰 ID 목록 조회
        List<Long> likeReviewIds = jpaQueryFactory
                .select(qLike.contentId)
                .from(qLike)
                .where(expression)
                .fetch();

        if (likeReviewIds.isEmpty()) {
            throw new IllegalArgumentException("좋아요한 리뷰가 없습니다.");
        }

        //좋아요한 리뷰조회
        List<Review> reviews = jpaQueryFactory
                .selectFrom(qReview)
                .where(qReview.id.in(likeReviewIds))
                .orderBy(qReview.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //좋아요한 리뷰 수 조회
        long total = jpaQueryFactory
                .selectFrom(qReview)
                .where(qReview.id.in(likeReviewIds))
                .fetchCount();

        // Review ->  ReviewResponseDto
        List<ReviewResponseDto> responseDtoList = reviews.stream()
                .map(ReviewResponseDto::new)
                .toList();
        return new PageImpl<>(responseDtoList,pageable,total);
    }
}
