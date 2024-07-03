package com.sparta.outsorcingproject.like;

import com.sparta.outsorcingproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/store/{storeId}")
    public ResponseEntity<String> storeLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long storeId
    ) {
        return likeService.storeLike(userDetails.getUser(), LikeContentType.STORE, storeId);
    }

    @PostMapping("/review/{reviewId}")
    public ResponseEntity<String> reviewLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long reviewId
    ){
        return likeService.reviewLike(userDetails.getUser(),LikeContentType.REVIEW,reviewId);
    }

    @PutMapping("/reload")
    public ResponseEntity<String> reloadLike() {

        likeService.reloadLike();
        return ResponseEntity.ok("좋아요 갱신 완료");
    }

}
