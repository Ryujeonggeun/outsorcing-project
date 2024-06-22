package com.sparta.outsorcingproject.entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class FollowerId implements Serializable {

	@Column(name = "follower_id")
	private Long followerId;

	@Column(name = "me_id")
	private Long meId;

	// 기본 생성자, getter, setter, equals, hashCode 메서드

	public FollowerId() {}

	public FollowerId(Long followerId, Long meId) {
		this.followerId = followerId;
		this.meId = meId;
	}

	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

	public Long getMeId() {
		return meId;
	}

	public void setMeId(Long meId) {
		this.meId = meId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FollowerId that = (FollowerId) o;
		return Objects.equals(followerId, that.followerId) && Objects.equals(meId, that.meId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(followerId, meId);
	}
}
