package com.sparta.blogpostspring.entity;

import com.sparta.blogpostspring.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class SubComment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcomment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "COMMENT_ID", nullable = false)
    private Comment comment;

    @NotBlank
    private String content;

    private int heartCount = 0;

    public SubComment(User user, Comment comment, CommentRequestDto commentRequestDto) {
        this.user = user;
        this.comment = comment;
        comment.getSubCommentList().add(this);
        this.content = commentRequestDto.getContent();
    }

    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

    public LocalDateTime getModifiedAt() {
        return super.getModifiedAt();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }
}
