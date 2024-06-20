package com.sparta.outsorcingproject.repository;

import com.sparta.outsorcingproject.entity.User;
import com.sparta.outsorcingproject.entity.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //signup
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    //profile
    //boolean existsUserByUseridAndStatus(String requestUserid, UserStatus userStatus);
//    Optional<User>findByUserid(String UserId);

}
