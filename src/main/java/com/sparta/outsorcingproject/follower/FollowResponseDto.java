package com.sparta.outsorcingproject.follower;

import lombok.Getter;

@Getter
public class FollowResponseDto {

	private final long followerId;
	private final long meId;

	public FollowResponseDto(Follower follower) {
		followerId = follower.getFollower().getId();
		meId = follower.getMe().getId();
	}
}
