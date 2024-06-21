package com.sparta.outsorcingproject.controller;

import com.sparta.outsorcingproject.dto.ReviewRequestDto;
import com.sparta.outsorcingproject.dto.ReviewResponseDto;
import com.sparta.outsorcingproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    //리뷰 등록
    //인증 인가 완성 되면 UserDetails 에서 뽑아와야함
    @PostMapping("/{ordersId}")
    public ResponseEntity<String> addReview(@PathVariable Long ordersId, @RequestBody ReviewRequestDto requestDto ) {


    return  reviewService.addReview(ordersId,requestDto);
    }


    // 리뷰 수정
    //인증 인가 완성 되면 UserDetails 에서 뽑아와야함
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody ReviewRequestDto requestDto) {
        return reviewService.updateReview(reviewId, requestDto);
    }

    // 단일 리뷰 조회
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Long reviewId) {
        return reviewService.getReview(reviewId);
    }

    //상점 리뷰 조회
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ReviewResponseDto>> getStoreReviews(@PathVariable Long storeId) {
        return reviewService.getStoreReviews(storeId);
    }


    // 리뷰 삭제
    //인증 인가 완성 되면 UserDetails 에서 뽑아와야함
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        return reviewService.deleteReview(reviewId);
    }

}



