package com.sparta.outsorcingproject.service;

import com.sparta.outsorcingproject.dto.ReviewRequestDto;
import com.sparta.outsorcingproject.dto.ReviewResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    public ResponseEntity<String> addReview(Long ordersId, ReviewRequestDto requestDto) {
        return null;
    }

    public ResponseEntity<String> updateReview(Long reviewId, ReviewRequestDto requestDto) {
        return null;
    }

    public ResponseEntity<ReviewResponseDto> getReview(Long reviewId) {
        return null;
    }

    public ResponseEntity<String> deleteReview(Long reviewId) {
        return null;
    }

    public ResponseEntity<List<ReviewResponseDto>> getStoreReviews(Long storeId) {
        return null;
    }
}
