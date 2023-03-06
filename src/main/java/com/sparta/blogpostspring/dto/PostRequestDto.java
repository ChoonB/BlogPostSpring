package com.sparta.blogpostspring.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PostRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
