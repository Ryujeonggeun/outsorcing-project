package com.sparta.outsorcingproject.follower;

import com.sparta.outsorcingproject.profile.ProfileResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sparta.outsorcingproject.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/follows")
public class FollowerController {

	public static final String SUCCESSFULLY_UNFOLLOWED = "언팔로우 되었습니다.";
	private final FollowService followService;


	@PostMapping("/{followerId}")
	public ResponseEntity<FollowResponseDto> follow(
		@PathVariable long followerId,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		FollowResponseDto responseDto = followService.follow(followerId, userDetails.getUser().getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@DeleteMapping("/{followerId}")
	public ResponseEntity<String> unfollow(
		@PathVariable long followerId,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		followService.unfollow(followerId, userDetails.getUser().getId());

		return ResponseEntity.ok().body(SUCCESSFULLY_UNFOLLOWED);
	}

	@GetMapping("/top10")
	public ResponseEntity<List<ProfileResponseDto>> getTop10FollowedUsers() {
		List<ProfileResponseDto> topFollowedUsers = followService.getTopFollowedUsers();
		return ResponseEntity.ok(topFollowedUsers);
	}
}
