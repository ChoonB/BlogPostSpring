package com.sparta.blogpostspring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HEART_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "User_ID",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = true)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "COMMENT_ID", nullable = true)
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "SUBCOMMENT_ID", nullable = true)
    private SubComment subComment;

//    게시글 좋아요
    public Heart(Post post, User user) {
        this.post = post;
        this.user = user;
        this.comment = null;
        this.subComment = null;
    }

//    댓글 좋아요
    public Heart(Comment comment, User user) {
        this.user = user;
        this.comment = comment;
        this.post = null;
        this.subComment = null;
    }

//    대댓글 좋아요
    public Heart(SubComment subComment, User user) {
        this.user = user;
        this.subComment = subComment;
        this.post = null;
        this.comment = null;
    }
}
