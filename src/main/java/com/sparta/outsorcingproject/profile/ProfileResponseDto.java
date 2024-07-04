package com.sparta.outsorcingproject.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.sparta.outsorcingproject.user.User;

@NoArgsConstructor
@Getter
public class ProfileResponseDto {
    
    private String username;
    private String introduce;
    private Long likedStoreCount;
    private Long likedReviewCount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String profileUrl;

    public ProfileResponseDto(User user) {
        this.username = user.getUsername();
        this.introduce = user.getIntroduce();
        this.profileUrl = user.getProfileUrl();
    }

    public ProfileResponseDto(User user,Long likedStoreCount,Long likedReviewCount) {
        this.username = user.getUsername();
        this.introduce = user.getIntroduce();
        this.profileUrl = user.getProfileUrl();
        this.likedStoreCount = likedStoreCount;
        this.likedReviewCount = likedReviewCount;
    }
}
