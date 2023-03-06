package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.dto.MsgResponseDto;
import com.sparta.blogpostspring.dto.PostDeleteDto;
import com.sparta.blogpostspring.dto.PostRequestDto;
import com.sparta.blogpostspring.dto.PostResponseDto;
import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

//    1. 전체게시글 조회 API
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

//    2. 게시글 작성 API
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
        return postService.createPost(postRequestDto, request);
    }

//    3. 선택 게시글 조회 API
    @GetMapping("/post/{id}")
    public PostResponseDto getSelectedPost(@PathVariable Long id){
        return postService.getSelectedPost(id);
    }

//    4. 선택 게시글 수정 API
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
        return postService.update(id, postRequestDto, request);
    }

//  5. 선택 게시글 삭제 API
    @DeleteMapping("/post/{id}")
    public MsgResponseDto deletePost(@PathVariable Long id, HttpServletRequest request) {
        return postService.deletePost(id, request);
    }

}
