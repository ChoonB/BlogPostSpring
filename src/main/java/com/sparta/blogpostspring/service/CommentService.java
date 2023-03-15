package com.sparta.blogpostspring.service;

import com.sparta.blogpostspring.dto.CommentRequestDto;
import com.sparta.blogpostspring.dto.CommentResponseDto;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.entity.Comment;
import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.entity.User;
import com.sparta.blogpostspring.entity.UserRoleEnum;
import com.sparta.blogpostspring.jwt.JwtUtil;
import com.sparta.blogpostspring.repository.CommentRepository;
import com.sparta.blogpostspring.repository.HeartRepository;
import com.sparta.blogpostspring.repository.PostRepository;
import com.sparta.blogpostspring.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final HeartRepository heartRepository;


    private Post findPostByPostId(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        return post;
    }

//    1. 댓글작성 메서드
    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Post post = findPostByPostId(postId);
        Comment comment = new Comment(post, user, commentRequestDto);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    //    2. 댓글 수정 메서드
    @Transactional
    public CommentResponseDto updateComment(Long postId, Long commentId ,CommentRequestDto commentRequestDto, User user) {
//        게시글 존재 여부 확인
        Post post = findPostByPostId(postId);
//        댓글 id로 댓글찾기
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );

//        user가 ADMIN이면 모든 게시글 수정 가능. 아니면 작성자 검증
        if (!comment.getUser().getId().equals(user.getId()) && !user.getRole().equals(UserRoleEnum.ADMIN)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);
    }

//  3. 댓글 삭제 메서드
    @Transactional
    public MessageResponseDto deleteComment(Long postId, Long commentId , User user) {
//      게시글 여부 확인
        Post post = findPostByPostId(postId);
//        댓글 id로 댓글찾기
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
//        user가 ADMIN이면 모든 게시글 수정 가능. 아니면 작성자 검증
        if (!comment.getUser().getId().equals(user.getId()) && !user.getRole().equals(UserRoleEnum.ADMIN)){
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        heartRepository.deleteAllByComment(comment);
        commentRepository.deleteById(commentId);
        return new MessageResponseDto("댓글을 성공적으로 삭제했습니다.", HttpStatus.OK);
    }

}
