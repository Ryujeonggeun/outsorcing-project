package com.sparta.outsorcingproject.like;

import com.sparta.outsorcingproject.security.UserDetailsImpl;
import com.sparta.outsorcingproject.store.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/store/{storeId}")
    public ResponseEntity<String> storeLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long storeId)
    {
        return likeService.storeLike(userDetails.getUser(), LikeContentType.STORE, storeId);
    }

    @PostMapping("/review/{reviewId}")
    public ResponseEntity<String> reviewLike(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long reviewId)
    {
        return likeService.reviewLike(userDetails.getUser(),LikeContentType.REVIEW,reviewId);
    }

    @GetMapping("/store")
    public ResponseEntity <List <StoreResponseDto>> likesStoreList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
       List<StoreResponseDto> likesStoreList = likeService.likesStoreList(userDetails.getUser());
       return ResponseEntity.ok(likesStoreList);
    }

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
