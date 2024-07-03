package com.sparta.outsorcingproject.profile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profile/get")
    public ResponseEntity<ProfileResponseDto> getProfile(@RequestParam String username) {
        ProfileRequestDto requestDto = new ProfileRequestDto(username);
        return profileService.showProfile(requestDto);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<ProfileResponseDto> updateProfile(@RequestBody @Valid ProfileModifyRequestDto modifyRequestDto) {
        return profileService.updateProfile(modifyRequestDto);
    }
}