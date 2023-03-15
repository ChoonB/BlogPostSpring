package com.sparta.blogpostspring.dto;

import com.sparta.blogpostspring.entity.Comment;
import com.sparta.blogpostspring.entity.SubComment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class CommentResponseDto {

    private Long id;
    private String username;
    private String content;
    private int heartCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<SubCommentResponseDto> subCommentList = new ArrayList<>();

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.heartCount = comment.getHeartCount();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();

        List<SubComment> subComments = comment.getSubCommentList();
        if (!subComments.isEmpty()){
            List<SubCommentResponseDto> subCommentResponseDtoList = new ArrayList<>();
            for (SubComment subComment : subComments) {
                subCommentResponseDtoList.add(new SubCommentResponseDto(subComment));
            }
            subCommentResponseDtoList.sort(Comparator.comparing(SubCommentResponseDto::getCreatedAt).reversed());
            this.subCommentList = subCommentResponseDtoList;
        }

    }


}
