package com.sparta.outsorcingproject.like;

import com.sparta.outsorcingproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
