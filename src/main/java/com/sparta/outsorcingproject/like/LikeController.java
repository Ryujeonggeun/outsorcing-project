package com.sparta.outsorcingproject.like;

import com.sparta.outsorcingproject.review.ReviewResponseDto;
import com.sparta.outsorcingproject.security.UserDetailsImpl;
import com.sparta.outsorcingproject.store.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    //상점 좋아요
    @PostMapping("/store/{storeId}")
    public ResponseEntity<String> storeLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long storeId)
    {
        return likeService.storeLike(userDetails.getUser(), LikeContentType.STORE, storeId);
    }

    //리뷰 좋아요
    @PostMapping("/review/{reviewId}")
    public ResponseEntity<String> reviewLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long reviewId)
    {
        return likeService.reviewLike(userDetails.getUser(),LikeContentType.REVIEW,reviewId);
    }

    //좋아요한 상점목록 조회
    @GetMapping("/store")
    public ResponseEntity <Page <StoreResponseDto>> likesStoreList(@AuthenticationPrincipal UserDetailsImpl userDetails,
    @RequestParam int page) {
       Page<StoreResponseDto> likesStoreList = likeService.likesStoreList(userDetails.getUser(),page);
       return ResponseEntity.ok(likesStoreList);
    }

    //좋아요한 리뷰목록 조회
    @GetMapping("/review")
    public ResponseEntity <Page <ReviewResponseDto>> likesReviewList(@AuthenticationPrincipal UserDetailsImpl userDetails,
    @RequestParam int page) {
        Page<ReviewResponseDto> likeReviewList = likeService.likesReviewList(userDetails.getUser(),page);
        return ResponseEntity.ok(likeReviewList);
    }


    //좋아요 취소
    @DeleteMapping("/{likeId}")
    public ResponseEntity<String> unLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long likeId)
    {
    return likeService.unlike(userDetails.getUser(),likeId);
    }

    @PutMapping("/reload")
    public ResponseEntity<String> reloadLike() {

        likeService.reloadLike();
        return ResponseEntity.ok("좋아요 갱신 완료");
    }

}
