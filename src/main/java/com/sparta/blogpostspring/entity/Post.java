package com.sparta.blogpostspring.entity;

import com.sparta.blogpostspring.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String contents;

    @Column(nullable = false)
    private String password;

//    전체게시글조회 DTO로 받아서 생성자 주입
    public Post(PostRequestDto postRequestDto) {
        this.author = postRequestDto.getAuthor();
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.password = postRequestDto.getPassword();
    }
}
