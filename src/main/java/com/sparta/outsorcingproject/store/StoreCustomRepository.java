package com.sparta.outsorcingproject.store;

import com.sparta.outsorcingproject.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreCustomRepository {

    Page<StoreResponseDto> findLikedStoresByUser(User user, Pageable pageable);
}
