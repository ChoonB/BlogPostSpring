package com.sparta.blogpostspring.cotroller;

import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.security.UserDetailsImpl;
import com.sparta.blogpostspring.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class HeartController {

    private final HeartService heartService;

//    1. 게시글 좋아요. 관리자는 좋아요 x
    @Secured("ROLE_USER")
    @GetMapping("/{postId}/heart")
    public MessageResponseDto pressPostHeart(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return heartService.pressPostHeart(postId, userDetails.getUser());
    }

//    2. 댓글 좋아요. 관리자는 좋아요 x
    @Secured("ROLE_USER")
    @GetMapping("/{postId}/comment/{commentId}/heart")
    public MessageResponseDto pressCommentHeart(@PathVariable Long postId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return heartService.pressCommentHeart(postId, commentId, userDetails.getUser());
    }

//    3. 대댓글 좋아요. 관리자는 좋아요 x
    @Secured("ROLE_USER")
    @GetMapping("/{postId}/comment/{commentId}/subcomment/{subCommentId}/heart")
    public MessageResponseDto pressSubCommentHeart(@PathVariable Long postId, @PathVariable Long commentId,
                                                   @PathVariable Long subCommentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return heartService.pressSubCommentHeart(postId, commentId, subCommentId, userDetails.getUser());
    }

}
