package com.sparta.outsorcingproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.outsorcingproject.entity.Follower;
import com.sparta.outsorcingproject.entity.User;

public interface FollowRepository extends JpaRepository<Follower, Long> {
	Optional<Follower> findByFollower(User follower);
}
