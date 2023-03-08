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

//    1. 댓글 작성 API
    @PostMapping("/{postId}/comment")
    public CommentResponseDto createComment(
            @PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.createComment(postId, commentRequestDto, request);
    }

//    아래는 commentId를 commentRequestDto에 넣으려 했으나 그렇게 될 경우 delete가 body로 보낼 content가 없어서 @Pathvariable로 처리

//    2. 댓글 수정 API ResponseEntity<?>를 사용해서 MessageResponseDto나 CommentResponseDto중 서비스에서 경우에따라 다르게 반환
    @PutMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long postId, @PathVariable Long commentId
            ,@RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.updateComment(postId, commentId ,commentRequestDto, request);
    }

//    3. 댓글 삭제 API
    @DeleteMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<MessageResponseDto> deleteComment(
            @PathVariable Long postId, @PathVariable Long commentId ,HttpServletRequest request) {
        MessageResponseDto msg = commentService.deleteComment(postId, commentId ,request);
        return new ResponseEntity<>(msg, msg.getStatus());
    }

}
