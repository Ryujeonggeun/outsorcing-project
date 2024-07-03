package com.sparta.outsorcingproject.like;

import com.sparta.outsorcingproject.user.User;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface LikeRepository extends JpaRepository<Like,Long> {

    // 게시글은 현재 존재 하는 상태 (Store store에서 검증함)
    // => 좋아요 요청하는 User가 해당 게시글에 좋아요를 눌렀는지, 안눌렀는지 확인하기 위해 사용
    boolean existsByUserAndContentTypeAndContentId(User user, LikeContentType contentType, Long contentId);
    Long countLikesByContentTypeAndContentId(LikeContentType contentType,Long contentId);
    Like findByUser(User user);


    default Like findLikeById(Long likeId, MessageSource messageSource){
        return findById(likeId).orElseThrow(()->new IllegalArgumentException(messageSource.getMessage("not.find.like",null, Locale.getDefault())));
    }

    Page<Like> findByUserAndContentType(User user, LikeContentType likeContentType, Pageable pageable);

}
