package com.sparta.blogpostspring.dto;

import com.sparta.blogpostspring.entity.SubComment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SubCommentResponseDto {

    private Long id;
    private String username;
    private String content;
    private int heartCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public SubCommentResponseDto(SubComment subComment) {
        this.id = subComment.getId();
        this.username = subComment.getUser().getUsername();
        this.content = subComment.getContent();
        this.heartCount = subComment.getHeartCount();
        this.createdAt = subComment.getCreatedAt();
        this.modifiedAt = subComment.getModifiedAt();
    }
}
