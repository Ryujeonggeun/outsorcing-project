package com.sparta.outsorcingproject.profile;

import com.sparta.outsorcingproject.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<User,Long>,ProfileCustomRepository{
}
