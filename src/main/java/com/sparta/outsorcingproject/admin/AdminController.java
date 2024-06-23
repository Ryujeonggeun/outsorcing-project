package com.sparta.outsorcingproject.admin;

import com.sparta.outsorcingproject.dto.MenuResponseDto;
import com.sparta.outsorcingproject.dto.OrdersResponseDto;
import com.sparta.outsorcingproject.dto.ReviewResponseDto;
import com.sparta.outsorcingproject.entity.Review;
import com.sparta.outsorcingproject.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //유저 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> allUser = adminService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    //사용자 권한 수정 삭제
    @PutMapping("/user/{userId}/role")
    public ResponseEntity<String> updateUserRole(@PathVariable Long userId, @RequestParam String newRole,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        adminService.updateUserRole(userId,newRole,userDetails.getUser());
        return ResponseEntity.ok(userId + "유저의 권한이 " + newRole + " 으로 수정되었습니다.");
    }

    //메뉴 전체 조회
    @GetMapping("/menus")
    public ResponseEntity<List<MenuResponseDto>> getAllMenus() {
        List<MenuResponseDto> allMenu = adminService.getAllMemus();
        return ResponseEntity.ok(allMenu);
    }

    //주문 전체 조회
    @GetMapping("/orders")
    public ResponseEntity<List<OrdersResponseDto>> getAllOrders() {
        List<OrdersResponseDto> allOder = adminService.getAllOrders();
        return ResponseEntity.ok(allOder);
    }

    //리뷰 전체 조회
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        List<ReviewResponseDto> allReview = adminService.getAllReviews();
        return ResponseEntity.ok(allReview);
    }

    //메뉴 생성

    //메뉴수정

    //메뉴 삭제

}
