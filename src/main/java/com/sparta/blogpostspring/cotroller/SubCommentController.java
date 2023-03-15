package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.dto.CommentRequestDto;
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




}
