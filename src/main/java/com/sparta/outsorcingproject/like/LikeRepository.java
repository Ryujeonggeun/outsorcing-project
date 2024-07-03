package com.sparta.outsorcingproject.like;

import com.sparta.outsorcingproject.store.Store;
import com.sparta.outsorcingproject.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {

    // 게시글은 현재 존재 하는 상태 (Store store에서 검증함)
    // => 좋아요 요청하는 User가 해당 게시글에 좋아요를 눌렀는지, 안눌렀는지 확인하기 위해 사용
    boolean existsByUserAndContentTypeAndContentId(User user, LikeContentType contentType, Long contentId);
    Long countLikesByContentTypeAndContentId(LikeContentType contentType,Long contentId);
    Like findByUser(User user);
}
