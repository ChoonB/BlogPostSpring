package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.dto.PostDeleteDto;
import com.sparta.blogpostspring.dto.PostRequestDto;
import com.sparta.blogpostspring.dto.PostResponseDto;
import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

//    홈으로 index.html 이거 필요한가? index.html이랑 이거 필요없어보임..
//    @GetMapping("/")
//    public ModelAndView home() {
//        return new ModelAndView("index");
//    }

//  아래의 메서드들 다 Dto로 전달하고 반환.

//    전체게시글 조회 API
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

//    게시글 작성 API
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);
    }

//    선택 게시글 조회 API
    @GetMapping("/post/{id}")
    public PostResponseDto getSelectedPost(@PathVariable Long id){
        return postService.getSelectedPost(id);
    }

//    선택 게시글 수정 API
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) throws Exception {
        return postService.update(id, postRequestDto);
    }

//    선택 게시글 삭제 API
//    @DeleteMapping("/api/post/{id}/{password}")
//    public boolean deletePost(@PathVariable Long id, @PathVariable String password) throws Exception {
//        return postService.deletePost(id, password);
//    }

    @DeleteMapping("/post/{id}")
    public boolean deletePost2(@PathVariable Long id, @RequestBody PostDeleteDto deleteDto) throws Exception {
        return postService.deletePost2(id, deleteDto.getPassword());
    }

}
