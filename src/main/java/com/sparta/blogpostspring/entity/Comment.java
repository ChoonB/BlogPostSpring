package com.sparta.blogpostspring.entity;

import com.sparta.blogpostspring.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @NotBlank
    private String content;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<SubComment> subCommentList = new ArrayList<>();

    private int heartCount = 0;


    public Comment(Post post, User user, CommentRequestDto commentRequestDto) {
        this.post = post;
        post.getCommentList().add(this);
        this.user = user;
        this.content = commentRequestDto.getContent();
    }

    public LocalDateTime getCreatedAt(){
        return super.getCreatedAt();
    }

    public LocalDateTime getModifiedAt(){
        return super.getModifiedAt();
    }

    public void update(CommentRequestDto commentRequestDto){
        this.content = commentRequestDto.getContent();
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }
}
