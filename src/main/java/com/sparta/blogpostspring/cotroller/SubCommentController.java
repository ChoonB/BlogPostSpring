package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.dto.CommentRequestDto;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.dto.SubCommentResponseDto;
import com.sparta.blogpostspring.security.UserDetailsImpl;
import com.sparta.blogpostspring.service.SubCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{postId}/comment/")
public class SubCommentController {

    private final SubCommentService subCommentService;

//    1. 대댓글 작성
    @PostMapping("/{commentId}/subcomment}")
    public SubCommentResponseDto createSubComment(@PathVariable Long commentId, @RequestBody @Valid CommentRequestDto commentRequestDto,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return subCommentService.createSubComment(commentId, commentRequestDto, userDetails.getUser());
    }

//    2. 대댓글 수정
    @PutMapping("/{commentId}/subcomment/{subCommentId}")
    public SubCommentResponseDto updateSubComment(@PathVariable Long commentId, @PathVariable Long subCommentId,
                                                  @RequestBody @Valid CommentRequestDto commentRequestDto,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return subCommentService.updateSubComment(commentId, subCommentId, commentRequestDto, userDetails.getUser());
    }

//    3. 대댓글 삭제
    @DeleteMapping("/{commentId}/subcomment/{subCommentId}")
    public MessageResponseDto deleteSubComment(@PathVariable Long commentId, @PathVariable Long subCommentId,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return subCommentService.deleteSubComment(commentId, subCommentId, userDetails.getUser());
    }

}
