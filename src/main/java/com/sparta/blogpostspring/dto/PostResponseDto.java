package com.sparta.blogpostspring.dto;

import com.sparta.blogpostspring.entity.Comment;
import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.repository.CommentRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class PostResponseDto {

    private Long id;
    private String username;
    private String title;
    private String content;
    private int heartCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList = new ArrayList<>();

    //    생성자
    public PostResponseDto(Post post) {

        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.id = post.getId();
        this.heartCount = post.getHeartCount();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();



        List<Comment> comments = post.getCommentList();

        if(!comments.isEmpty()){
            List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
            for (Comment comment : comments) {
                commentResponseDtoList.add(new CommentResponseDto(comment));
            }
            commentResponseDtoList.sort(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed());
            this.commentList = commentResponseDtoList;
        }
    }
}
