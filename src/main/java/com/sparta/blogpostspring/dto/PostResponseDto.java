package com.sparta.blogpostspring.dto;

import com.sparta.blogpostspring.entity.Comment;
import com.sparta.blogpostspring.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private String username;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<Comment> commentList;

    //    생성자
    public PostResponseDto(Post post) {

        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.id = post.getId();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = post.getCommentList();
        this.commentList.sort(Comparator.comparing(Comment::getCreatedAt).reversed());
    }
}
