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
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private JwtUtil jwtUtil;

//    토큰 검증 메서드
    private User findUserByToken(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token == null) {
            throw new IllegalArgumentException("JWT Token이 없습니다.");
        }

        if (jwtUtil.validateToken(token)) {
            claims = jwtUtil.getUserInfoFromToken(token);
        } else {
            throw new IllegalArgumentException("Token Error");
        }

        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        return user;
    }

//    게시글 id로 DB에서 게시글 찾기
        private Post findPostById(Long id) {
            return postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
            );
        }

//    1. 댓글작성 메서드
    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        User user = findUserByToken(request);
        Post post = findPostById(id);
        Comment comment = new Comment(post, user, commentRequestDto);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    //    2. 댓글 수정 메서드
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        User user = findUserByToken(request);
//        댓글 id로 댓글찾기
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
//        Post가 있는지 확인
        Post post = findPostById(comment.getPost().getId());
//        user가 ADMIN이면 모든 게시글 수정 가능. 아니면 작성자 검증
        if (user.getRole().equals(UserRoleEnum.ADMIN)){
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        }
        if (!comment.getUser().equals(user)){
            throw new IllegalArgumentException("해당 댓글의 작성자가 아닙니다.");
        }
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);

    }

//  3. 댓글 삭제 메서드
    public MessageResponseDto deleteComment(Long id, HttpServletRequest request) {
        User user = findUserByToken(request);
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        Post post = findPostById(comment.getPost().getId());
//        user가 ADMIN이면 모든 게시글 수정 가능. 아니면 작성자 검증
        if (user.getRole().equals(UserRoleEnum.ADMIN)){
            commentRepository.deleteById(id);
            return new MessageResponseDto("댓글을 삭제했습니다.", HttpStatus.OK);
        }
        if (!comment.getUser().equals(user)){
            throw new IllegalArgumentException("해당 댓글의 작성자가 아닙니다.");
        }
        commentRepository.deleteById(id);
        return new MessageResponseDto("댓글을 삭제했습니다.", HttpStatus.OK);
    }
}
