package com.sparta.blogpostspring.entity;

import com.sparta.blogpostspring.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

//    전체게시글조회 DTO로 받아서 생성자 주입
    public Post(PostRequestDto postRequestDto) {
        this.author = postRequestDto.getAuthor();
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.password = postRequestDto.getPassword();
    }

//    타임스탬프 2요소 게터 생성
    public LocalDateTime getCreatedAt(){
        return super.getCreatedAt();
    }

    public LocalDateTime getModifiedAt(){
        return super.getModifiedAt();
    }

    public void update(PostRequestDto postRequestDto) {
        this.author = postRequestDto.getAuthor();
        this.content = postRequestDto.getContent();
        this.title = postRequestDto.getTitle();

    }
}
