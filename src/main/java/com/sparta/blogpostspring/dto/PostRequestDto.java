package com.sparta.blogpostspring.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequestDto {
    private String author;
    private String title;
    private String content;
    private String password;


}
