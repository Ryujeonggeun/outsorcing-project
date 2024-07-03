package com.sparta.outsorcingproject.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	//signup
	Optional<User> findByUsername(String username);

	Optional<User> findByKakaoId(Long kakaoId);
}
