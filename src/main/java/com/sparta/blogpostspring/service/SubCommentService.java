package com.sparta.blogpostspring.service;

import com.sparta.blogpostspring.dto.CommentRequestDto;
import com.sparta.blogpostspring.dto.SubCommentResponseDto;
import com.sparta.blogpostspring.entity.Comment;
import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.entity.SubComment;
import com.sparta.blogpostspring.entity.User;
import com.sparta.blogpostspring.repository.CommentRepository;
import com.sparta.blogpostspring.repository.HeartRepository;
import com.sparta.blogpostspring.repository.SubCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class SubCommentService {

    private final CommentRepository commentRepository;
    private final SubCommentRepository subCommentRepository;
    private final HeartRepository heartRepository;

    @Transactional
    public SubCommentResponseDto createSubComment(Long commentId, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        SubComment subComment = new SubComment(user, comment, commentRequestDto);
        subCommentRepository.saveAndFlush(subComment);
        return new SubCommentResponseDto(subComment);
    }
}
