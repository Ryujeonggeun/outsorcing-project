package com.sparta.outsorcingproject.review;


import com.sparta.outsorcingproject.orders.Orders;
import com.sparta.outsorcingproject.store.Store;
import com.sparta.outsorcingproject.entity.Timestamped;
import com.sparta.outsorcingproject.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Column
    private Long likeCount;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String review;
    private Double rate;


    public Review(User user, Orders orders, Store store, String review, Double rate) {
        this.user = user;
        this.orders = orders;
        this.store = store;
        this.review = review;
        this.rate = rate;
        this.likeCount = 0L;
    }

    public void updateReview(String review, Double rate) {
        this.review = review;
        this.rate = rate;
    }

    public void addCount(){
        this.likeCount++;
    }

    public void subtractCount(){
        this.likeCount--;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
}
