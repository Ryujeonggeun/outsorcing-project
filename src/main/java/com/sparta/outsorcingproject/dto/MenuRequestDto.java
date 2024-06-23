package com.sparta.outsorcingproject.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuRequestDto {
    @NotBlank
    private String name;

    @Positive(message = "0이 아닌 양수만 가능합니다.")
    @Max(value = 1000000, message = "100만원을 초과할 수 없습니다.")
    private long price;

    private String description;
}
