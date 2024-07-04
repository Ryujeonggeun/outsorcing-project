package com.sparta.outsorcingproject.profile;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.outsorcingproject.like.LikeContentType;
import com.sparta.outsorcingproject.like.QLike;
import com.sparta.outsorcingproject.review.QReview;
import com.sparta.outsorcingproject.store.QStore;
import com.sparta.outsorcingproject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileCustomRepositoryImpl implements ProfileCustomRepository{

    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    @Override
    public ProfileResponseDto findUserProfileWithLikes(User user) {
        QLike qLike = QLike.like;
        QStore qStore = QStore.store;
        QReview qReview = QReview.review1;



        //내가 좋아요한 상점 수 조회
        long likedStoreCount = jpaQueryFactory
                .select(qLike.count())
                .from(qLike)
                .where(qLike.contentType.eq(LikeContentType.STORE)
                        .and(qLike.user.eq(user)))
                .fetchFirst();



        //내가 좋아요한 리뷰 수 조회

        long likedReviewCount = jpaQueryFactory
                .select(qLike.count())
                .from(qLike)
                .where(qLike.contentType.eq(LikeContentType.REVIEW)
                        .and(qLike.user.eq(user)))
                .fetchFirst();

        return new ProfileResponseDto(user,likedStoreCount,likedReviewCount);




    }
}
