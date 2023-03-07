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
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

//    1. 댓글 작성 API 여기서 id는 Post의 id / 위아래 id가 뜻하는게 다른데 이렇게 짜는게 맞나?
    @PostMapping("/post/{id}")
    public CommentResponseDto createComment(
            @PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.createComment(id, commentRequestDto, request);
    }

//    2. 댓글 수정 API 여기서 id는 comment의 id
    @PutMapping("/{id}")
    public CommentResponseDto updateComment(
            @PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.updateComment(id, commentRequestDto, request);
    }

//    3. 댓글 삭제 API 여기서 id는 comment의 id
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDto> deleteComment(
            @PathVariable Long id, HttpServletRequest request) {
        MessageResponseDto msg = commentService.deleteComment(id, request);
        return new ResponseEntity<>(msg, msg.getStatus());
    }

}
