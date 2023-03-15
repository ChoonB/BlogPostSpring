package com.sparta.blogpostspring.service;

import com.sparta.blogpostspring.dto.CommentRequestDto;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.dto.SubCommentResponseDto;
import com.sparta.blogpostspring.entity.*;
import com.sparta.blogpostspring.repository.CommentRepository;
import com.sparta.blogpostspring.repository.HeartRepository;
import com.sparta.blogpostspring.repository.PostRepository;
import com.sparta.blogpostspring.repository.SubCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class SubCommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final SubCommentRepository subCommentRepository;
    private final HeartRepository heartRepository;

    @Transactional
    public SubCommentResponseDto createSubComment(Long postId, Long commentId, CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        SubComment subComment = new SubComment(user, comment, commentRequestDto);
        subCommentRepository.saveAndFlush(subComment);
        return new SubCommentResponseDto(subComment);
    }

    @Transactional
    public SubCommentResponseDto updateSubComment(Long postId, Long commentId, Long subCommentId, CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        SubComment subComment = subCommentRepository.findById(subCommentId).orElseThrow(
                () -> new IllegalArgumentException("대댓글을 찾을 수 없습니다.")
        );
//        User ADMIN 검증
        if (!subComment.getUser().getId().equals(user.getId()) && !user.getRole().equals(UserRoleEnum.ADMIN)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        subComment.update(commentRequestDto);
        return new SubCommentResponseDto(subComment);
    }

    @Transactional
    public MessageResponseDto deleteSubComment(Long postId, Long commentId, Long subCommentId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        SubComment subComment = subCommentRepository.findById(subCommentId).orElseThrow(
                () -> new IllegalArgumentException("대댓글을 찾을 수 없습니다.")
        );
        if (!subComment.getUser().getId().equals(user.getId()) && !user.getRole().equals(UserRoleEnum.ADMIN)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        heartRepository.deleteAllBySubComment(subComment);
        subCommentRepository.deleteById(subCommentId);
        return new MessageResponseDto("대댓글을 성공적으로 삭제했습니다.", HttpStatus.OK);
    }
}
