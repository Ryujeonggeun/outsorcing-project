package com.sparta.outsorcingproject.like;

import com.sparta.outsorcingproject.store.Store;
import com.sparta.outsorcingproject.user.User;
import com.sparta.outsorcingproject.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final StoreRepository storeRepository;
    private final MessageSource messageSource;

    @Transactional
    public ResponseEntity<String> storeLike(User user, LikeContentType contentType, Long contentId) {

        //존재하는 상점인지 확인
        Store store =  storeRepository.findStoreById(contentId,messageSource);

        //자신의 상점이면 예외처리
        if (store.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("자신의 상점에는 좋아요를 할 수 없습니다.");
        }

        //이미 좋아요 했는지 확인하기
        Boolean checkExist = likeRepository.existsByUserAndContentTypeAndContentId(user,contentType,contentId);

        if (!checkExist) {
            Like storeLike = new Like(user, contentType, contentId);
            store.addCount();
            likeRepository.save(storeLike);
        } else {
            throw new IllegalArgumentException("이미 좋아요를 한 상태입니다");
        }

        return ResponseEntity.ok("좋아요 완료");
    }
}
