package com.sparta.blogpostspring.entity;

import com.sparta.blogpostspring.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    private int heartCount = 0;


//    전체게시글조회 DTO로 받아서 생성자 주입
    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.user = user;
    }

//    타임스탬프 2요소 게터 생성
    public LocalDateTime getCreatedAt(){
        return super.getCreatedAt();
    }

    public LocalDateTime getModifiedAt(){
        return super.getModifiedAt();
    }

//    update 메서드 생성
    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }
}
