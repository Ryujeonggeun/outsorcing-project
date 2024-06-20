package com.sparta.outsorcingproject.dto;

import com.sparta.outsorcingproject.entity.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuResponseDto {
    private String name;

    private long price;

    private String description;

    public MenuResponseDto(Menu menu) {
        name = menu.getName();
        price = menu.getPrice();
        description = menu.getDescription();
    }
}
