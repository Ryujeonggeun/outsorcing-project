package com.sparta.outsorcingproject.admin;

import com.sparta.outsorcingproject.entity.User;
import com.sparta.outsorcingproject.entity.UserRoleEnum;
import com.sparta.outsorcingproject.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class AdminService {

    private static final Logger log = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageSource messageSource;

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new).toList();
    }

    @Transactional
    public void updateUserRole(Long userId, String newRole, User user) {

        //대문자로 변경
       newRole = newRole.toUpperCase();

        // ADMIN/User 둘 중 하나로 들어왔는지 체크
        if (!("USER".equals(newRole) | "ADMIN".equals(newRole))) {
            throw new IllegalArgumentException("잘못된 값을 입력하셨습니다.\n가능한 입력: user/admin");
        }

        //모양 맞춰주기
        newRole = "ROLE_" + newRole;

        // userId로 user 찾기
        User getUser = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException(
                        messageSource.getMessage("not.find.user", null, Locale.getDefault())
                )
        );

        // 바꾸려는 권한이 원래 있던 권한가 같으면 예외 처리
        if (newRole.equals(getUser.getRole().getAuthority())) {
        throw new IllegalArgumentException("바꾸려는 유저의 권한은이미 " + newRole + "입니다");
        }

        //변경후 누가 변경했는지 로그 찍기
        UserRoleEnum userRoleEnum = UserRoleEnum.valueOf(newRole.substring(5));
        getUser.setRole(userRoleEnum);
        log.info(user.getUsername() + "가 UserId: " + userId + " 의 권한을 " + newRole + " 로 변경하였습니다." );
        }

}
