package com.sparta.blogpostspring.dto;

import com.sparta.blogpostspring.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
    private String username;
    private String title;
    private String content;
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

//    생성자
    public PostResponseDto(Post post) {

        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.id = post.getId();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
