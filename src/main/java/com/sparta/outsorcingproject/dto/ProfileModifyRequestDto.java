package com.sparta.outsorcingproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileModifyRequestDto {
    private String username;
    private String introduce;
    private String oldPassword;
    private String newPassword;
}