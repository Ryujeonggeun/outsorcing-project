package com.sparta.outsorcingproject.profile;

import lombok.Getter;

@Getter
public class ProfileRequestDto {
    private String username;

    public ProfileRequestDto(String username) {
        this.username = username;
    }
}