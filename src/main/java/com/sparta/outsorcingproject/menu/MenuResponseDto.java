package com.sparta.outsorcingproject.menu;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MenuResponseDto {
    private long id;

    private String name;

    private long price;

    private String description;

    private LocalDateTime createdAt;

    public MenuResponseDto(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
    }

    public MenuResponseDto(Menu menu,LocalDateTime time) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.description = menu.getDescription();
        this.createdAt = LocalDateTime.now();

    }
}
