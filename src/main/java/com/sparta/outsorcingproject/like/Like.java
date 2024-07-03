package com.sparta.outsorcingproject.like;

import com.sparta.outsorcingproject.store.Store;
import com.sparta.outsorcingproject.entity.Timestamped;
import com.sparta.outsorcingproject.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "likes")
public class Like extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LikeContentType contentType;

    @Column(nullable = false)
    private Long contentId;



    public Like(User user, LikeContentType contentType, Long contentId) {
        this.user = user;
        this.contentType = contentType;
        this.contentId = contentId;
    }
}

enum LikeContentType{
    REVIEW,STORE
}
