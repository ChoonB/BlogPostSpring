package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.dto.PostRequestDto;
import com.sparta.blogpostspring.dto.PostResponseDto;
import com.sparta.blogpostspring.security.UserDetailsImpl;
import com.sparta.blogpostspring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public PostResponseDto createPost(@RequestBody @Valid PostRequestDto postRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(postRequestDto, userDetails.getUser());
    }

//    3. 선택 게시글 조회 API
    @GetMapping("/post/{PostId}")
    public PostResponseDto getSelectedPost(@PathVariable Long PostId){
        return postService.getSelectedPost(PostId);
    }

//    4. 선택 게시글 수정 API
    @PutMapping("/post/{PostId}")
    public PostResponseDto updatePost(@PathVariable Long PostId, @RequestBody @Valid PostRequestDto postRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.update(PostId, postRequestDto, userDetails.getUser());
    }

//  5. 선택 게시글 삭제 API
    @DeleteMapping("/post/{PostId}")
    public MessageResponseDto deletePost(@PathVariable Long PostId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(PostId, userDetails.getUser());
    }

}
