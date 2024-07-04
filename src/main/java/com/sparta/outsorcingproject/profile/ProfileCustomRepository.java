package com.sparta.outsorcingproject.profile;

import com.sparta.outsorcingproject.user.User;

public interface ProfileCustomRepository {
    ProfileResponseDto findUserProfileWithLikes(User user);
}
