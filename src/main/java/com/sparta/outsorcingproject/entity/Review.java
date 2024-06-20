package com.sparta.outsorcingproject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToOne
//    @JoinColumn("order_id")
//    private Order order;
//
//    @ManyToOne
//    @JoinColumn("store_id")
//    private Store store;

    private String review;
    private Double rate;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
