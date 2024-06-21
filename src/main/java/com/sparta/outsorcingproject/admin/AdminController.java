package com.sparta.outsorcingproject.admin;

import com.sparta.outsorcingproject.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //유저 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return null;
    }

    //사용자 권한 수정 삭제


    //메뉴 전체 조회

    //주문 전체 조회

    //리뷰 전체 조회

    //메뉴 생성

    //메뉴수정

    //메뉴 삭제

}
