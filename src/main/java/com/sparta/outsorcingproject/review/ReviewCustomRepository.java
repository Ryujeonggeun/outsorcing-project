package com.sparta.outsorcingproject.review;

import com.sparta.outsorcingproject.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewCustomRepository {

    Page<ReviewResponseDto>findLikedReviewByUser(User user, Pageable pageable);
}
