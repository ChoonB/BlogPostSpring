package com.sparta.blogpostspring.entity;

import com.sparta.blogpostspring.dto.PostRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PostTest {

    @Mock
    User user;


    @Nested
    @DisplayName("post 생성")
    class createPost{

//        private User user;
        private String title = "제목입니당";
        private String content = "내용입니당";

        PostRequestDto setUp() {
            PostRequestDto postRequestDto = new PostRequestDto();
            postRequestDto.setTitle(title);
            postRequestDto.setContent(content);
            return postRequestDto;
        }


        @Test
        @DisplayName("정상케이스")
        void createPost_Normal(){
//            given
            PostRequestDto postRequestDto = setUp();
//            when
            Post post = new Post(postRequestDto, user);

//            then
            assertNull(post.getId());
            assertEquals(0, post.getHeartCount());
            assertEquals(title, post.getTitle());
            assertEquals(content, post.getContent());
            assertEquals(0, post.getCommentList().size());
        }


    }

}