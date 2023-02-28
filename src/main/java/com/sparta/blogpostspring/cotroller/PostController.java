package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

//    홈으로 index.html 이거 필요한가? index.html이랑 이거 필요없어보임..
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

//    전체게시글 조회 API
    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }




}
