package com.sparta.outsorcingproject.like;

import com.sparta.outsorcingproject.review.Review;
import com.sparta.outsorcingproject.review.ReviewRepository;
import com.sparta.outsorcingproject.review.ReviewResponseDto;
import com.sparta.outsorcingproject.store.Store;
import com.sparta.outsorcingproject.store.StoreResponseDto;
import com.sparta.outsorcingproject.user.User;
import com.sparta.outsorcingproject.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final StoreRepository storeRepository;
    private final MessageSource messageSource;
    private final ReviewRepository reviewRepository;


    @Transactional
    public ResponseEntity<String> storeLike(User user, LikeContentType contentType, Long contentId) {

        //존재하는 상점인지 확인
        Store store = storeRepository.findStoreById(contentId, messageSource);

        //자신의 상점이면 예외처리
        if (store.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("자신의 상점에는 좋아요를 할 수 없습니다.");
        }

        //이미 좋아요 했는지 확인하기
        Boolean checkExist = likeRepository.existsByUserAndContentTypeAndContentId(user, contentType, contentId);

        if (!checkExist) {
            Like storeLike = new Like(user, contentType, contentId);
            store.addCount();
            likeRepository.save(storeLike);

        } else {
            throw new IllegalArgumentException("이미 좋아요를 한 상태입니다");
        }

        return ResponseEntity.ok("좋아요 완료");
    }

    @Transactional
    public ResponseEntity<String> reviewLike(User user, LikeContentType contentType, Long contentId) {

        //존재하는 리뷰인지 확인
        Review review = reviewRepository.findReviewById(contentId, messageSource);

        //자신의 리뷰면 예외처리
        if (review.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("자신의 리뷰에는 좋아요를 할 수 없습니다.");
        }

        //이미 좋아요 했는지 확인하기
        Boolean isExist = likeRepository.existsByUserAndContentTypeAndContentId(user, contentType, contentId);

        if (!isExist) {
            Like reviewLike = new Like(user, contentType, contentId);
            review.addCount();
            likeRepository.save(reviewLike);

        } else {
            throw new IllegalArgumentException("이미 좋아요를 한 상태입니다");
        }

        return ResponseEntity.ok("좋아요 완료");
    }

    @Transactional
    public ResponseEntity<String> unlike(User user, Long likeId) {

        //존재하는 좋아요인지 확인
        Like like = likeRepository.findLikeById(likeId, messageSource);

        //본인의 좋아요인지 확인하기
        if (!like.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("자신의 좋아요만 취소 할 수 있습니다.");
        }
        //좋아요가 상점일때
        if (like.getContentType().equals(LikeContentType.STORE)) {
            Store store = storeRepository.findStoreById(like.getContentId(), messageSource);
            //좋아요를 삭제하고 해당 상점의 좋아요를 하나 내림
            likeRepository.delete(like);
            store.subtractCount();
        }

        //좋아요가 리뷰일때
        if (like.getContentType().equals(LikeContentType.REVIEW)) {
            Review review = reviewRepository.findReviewById(like.getContentId(), messageSource);
            //좋아요를 삭제하고 해당 리뷰의 좋아요를 하나 내림
            likeRepository.delete(like);
            review.subtractCount();
        }

        String message = like.getContentType() + " 좋아요 취소 완료";

        return ResponseEntity.ok(message);
    }

    // 좋아요한 상점 리스트
    @Transactional(readOnly = true)
    public Page<StoreResponseDto> likesStoreList(User user, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return storeRepository.findLikedStoresByUser(user, pageable);
    }

    //좋아요한 리뷰 리스트
    @Transactional(readOnly = true)
    public Page<ReviewResponseDto> likesReviewList(User user, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return reviewRepository.findLikedReviewByUser(user, pageable);
    }


    // 좋아요한 상점 리스트
//    public Page<StoreResponseDto> likesStoreList(User user, int page) {
//        //페이지네이션 및 정렬
//        Pageable pageable =  PageRequest.of(page,5, Sort.by(Sort.Direction.DESC, "createdAt"));
//        //유저가 좋아요한 상점이 있는지 확인
//        Page<Like> likes = likeRepository.findByUserAndContentType(user, LikeContentType.STORE,pageable);
//        if (likes.isEmpty()) {
//        throw new IllegalArgumentException("좋아요한 상점이 없습니다.");
//        }
//
//        Page<StoreResponseDto> likeStoreList = likes.map(like -> {
//            Store store = storeRepository.findStoreById(like.getContentId(), messageSource);
//            return new StoreResponseDto(store);
//        });
//
//        return likeStoreList;
//    }

//    //좋아요한 리뷰 리스트
//    public Page<ReviewResponseDto> likereviewList(User user, int page) {
//
//        //페이지네이션 및 정렬
//
//        Pageable pageable = PageRequest.of(page,5, Sort.by(Sort.Direction.DESC, "createdAt"));
//        //유저가 좋아요한 리뷰가 있는지
//        Page<Like> likes = likeRepository.findByUserAndContentType(user, LikeContentType.REVIEW,pageable);
//        if (likes.isEmpty()) {
//            throw new IllegalArgumentException("좋아요한 리뷰가 없습니다.");
//        }
//
//        Page<ReviewResponseDto> likeReviewList = likes.map(like -> {
//            Review review = reviewRepository.findReviewById(like.getContentId(), messageSource);
//            return new ReviewResponseDto(review);
//        });
//
//
//        return likeReviewList;
//    }

    // 좋아요 리로딩
    @Transactional
    public void reloadLike() {
        List<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            Long likeCount = likeRepository.countLikesByContentTypeAndContentId(LikeContentType.STORE, store.getId());
            store.setLikeCount(likeCount);
            storeRepository.save(store);
        }

        List<Review> reviews = reviewRepository.findAll();
        for (Review review : reviews) {
            Long likeCount = likeRepository.countLikesByContentTypeAndContentId(LikeContentType.REVIEW, review.getId());
            review.setLikeCount(likeCount);
            reviewRepository.save(review);
        }
    }


}
