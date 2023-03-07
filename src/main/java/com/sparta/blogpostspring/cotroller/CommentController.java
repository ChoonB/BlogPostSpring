package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.dto.CommentRequestDto;
import com.sparta.blogpostspring.dto.CommentResponseDto;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class CommentController {

    private final CommentService commentService;

//    api/post{postId}/commnet/{commentId}
//    1. 댓글 작성 API 여기서 id는 Post의 id
    @PostMapping("/{postId}/comment")
    public CommentResponseDto createComment(
            @PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.createComment(postId, commentRequestDto, request);
    }

//    2. 댓글 수정 API 여기서 id는 post의 id
    @PutMapping("/{postId}/comment/{commentId}")
    public CommentResponseDto updateComment(
            @PathVariable Long postId, @PathVariable Long commentId
            ,@RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.updateComment(postId, commentId ,commentRequestDto, request);
    }

//    3. 댓글 삭제 API 여기서 id는 post의 id
    @DeleteMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<MessageResponseDto> deleteComment(
            @PathVariable Long postId, @PathVariable Long commentId ,HttpServletRequest request) {
        MessageResponseDto msg = commentService.deleteComment(postId, commentId ,request);
        return new ResponseEntity<>(msg, msg.getStatus());
    }

}
