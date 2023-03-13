package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.dto.CommentRequestDto;
import com.sparta.blogpostspring.dto.CommentResponseDto;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class CommentController {

    private final CommentService commentService;


//    1. 댓글 작성 API
    @PostMapping("/{postId}/comment")
    public CommentResponseDto createComment(
            @PathVariable Long postId, @RequestBody @Valid CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.createComment(postId, commentRequestDto, request);
    }

//    아래는 commentId를 commentRequestDto에 넣으려 했으나 그렇게 될 경우 delete가 body로 보낼 content가 없어서 @Pathvariable로 처리

//    2. 댓글 수정
    @PutMapping("/{postId}/comment/{commentId}")
    public CommentResponseDto updateComment(
            @PathVariable Long postId, @PathVariable Long commentId
            ,@RequestBody @Valid CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.updateComment(postId, commentId ,commentRequestDto, request);
    }

//    3. 댓글 삭제 API
    @DeleteMapping("/{postId}/comment/{commentId}")
    public MessageResponseDto deleteComment(
            @PathVariable Long postId, @PathVariable Long commentId ,HttpServletRequest request) {
        return commentService.deleteComment(postId, commentId ,request);
    }

}
