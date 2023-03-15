package com.sparta.blogpostspring.service;

import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.entity.*;
import com.sparta.blogpostspring.repository.CommentRepository;
import com.sparta.blogpostspring.repository.HeartRepository;
import com.sparta.blogpostspring.repository.PostRepository;
import com.sparta.blogpostspring.repository.SubCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final SubCommentRepository subCommentRepository;

//    게시글 좋아요
    @Transactional
    public MessageResponseDto pressPostHeart(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );

        List<Heart> heartList = heartRepository.findAllByPost(post);
//        이미 좋아요 눌렀으면 좋아요 누른 기록 없에고 좋아요 숫자 -1
        if(!heartList.isEmpty()) {
            for (Heart heart : heartList) {
                if (heart.getUser().getId().equals(user.getId())) {
                    heartRepository.delete(heart);
                    post.setHeartCount(heartList.size()-1);
                    return new MessageResponseDto("해당 게시글에 '좋아요'를 취소했습니다.", HttpStatus.OK);
                }
            }
        }
//        좋아요 안눌렀으면 좋아요 추가하고 숫자 +1
        Heart heart = new Heart(post, user);
        heartRepository.saveAndFlush(heart);
        post.setHeartCount(heartList.size()+1);
        return new MessageResponseDto("해당 게시글에 '좋아요'를 눌렀습니다.", HttpStatus.OK);
    }

//    댓글 좋아요
    @Transactional
    public MessageResponseDto pressCommentHeart(Long postId, Long commentId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );

        List<Heart> heartList = heartRepository.findAllByComment(comment);
//        이미 좋아요 눌렀으면 좋아요 누른 기록 없에고 좋아요 숫자 -1
        if (!heartList.isEmpty()) {
            for (Heart heart : heartList) {
                if (heart.getUser().getId().equals(user.getId())) {
                    heartRepository.delete(heart);
                    comment.setHeartCount(heartList.size()-1);
                    return new MessageResponseDto("해당 댓글에 '좋아요'를 취소했습니다.", HttpStatus.OK);
                }
            }
        }
//        좋아요 안눌렀으면 좋아요 기록 추가하고 숫자 +1
        Heart heart = new Heart(comment, user);
        heartRepository.saveAndFlush(heart);
        comment.setHeartCount(heartList.size()+1);
        return new MessageResponseDto("해당 댓글에 좋아요를 눌렀습니다.", HttpStatus.OK);
    }

    @Transactional
    public MessageResponseDto pressSubCommentHeart(Long postId, Long commentId, Long subCommentId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다.")
        );
        SubComment subComment = subCommentRepository.findById(subCommentId).orElseThrow(
                () -> new IllegalArgumentException("대댓글을 찾을 수 없습니다.")
        );

        List<Heart> heartList = heartRepository.findAllBySubComment(subComment);
        //        이미 좋아요 눌렀으면 좋아요 누른 기록 없에고 좋아요 숫자 -1
        if (!heartList.isEmpty()) {
            for (Heart heart : heartList) {
                if (heart.getUser().getId().equals(user.getId())) {
                    heartRepository.delete(heart);
                    subComment.setHeartCount(heartList.size()-1);
                    return new MessageResponseDto("해당 댓글에 '좋아요'를 취소했습니다.", HttpStatus.OK);
                }
            }
        }
        //        좋아요 안눌렀으면 좋아요 기록 추가하고 숫자 +1
        Heart heart = new Heart(subComment, user);
        heartRepository.saveAndFlush(heart);
        subComment.setHeartCount(heartList.size()+1);
        return new MessageResponseDto("해당 댓글에 좋아요를 눌렀습니다.", HttpStatus.OK);

    }
}
