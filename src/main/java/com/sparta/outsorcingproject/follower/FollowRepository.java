package com.sparta.outsorcingproject.follower;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.outsorcingproject.user.User;

public interface FollowRepository extends JpaRepository<Follower, FollowerId>,FollowCustomRepository {
	Optional<Follower> findByFollower(User follower);
	Optional<Follower> findByFollowerAndMe(User follower, User me);
}
