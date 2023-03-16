package com.sparta.blogpostspring;

import com.sparta.blogpostspring.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogPostSpringApplicationTests {

    @Test
    void contextLoads() {
        List<Comment> comment = new ArrayList<>();
        if (comment != null) System.out.println("111");
        if (!comment.isEmpty()) System.out.println("222");
        if (!(comment.size()==0)) System.out.println("333");

    }

}
