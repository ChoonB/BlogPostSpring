package com.sparta.blogpostspring.service;

import com.sparta.blogpostspring.dto.CommentResponseDto;
import com.sparta.blogpostspring.dto.MessageResponseDto;
import com.sparta.blogpostspring.dto.PostRequestDto;
import com.sparta.blogpostspring.dto.PostResponseDto;
import com.sparta.blogpostspring.entity.Comment;
import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.entity.User;
import com.sparta.blogpostspring.entity.UserRoleEnum;
import com.sparta.blogpostspring.jwt.JwtUtil;
import com.sparta.blogpostspring.repository.CommentRepository;
import com.sparta.blogpostspring.repository.PostRepository;
import com.sparta.blogpostspring.repository.UserRepository;
import com.sparta.blogpostspring.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 게시글 id로 DB에서 게시글 찾기
    private Post findPostById(Long PostId){
        return postRepository.findById(PostId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
    }

    //  1. 전체게시글 조회 메서드
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        for (Post post : postList) {
            postResponseDtoList.add(new PostResponseDto(post));
        }
        return postResponseDtoList;
    }


//    2. 게시글 작성 메서드
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto, user);
        postRepository.saveAndFlush(post);
        return new PostResponseDto(post);
    }

//    3. 선택 게시글 조회 메서드
    @Transactional(readOnly = true)
    public PostResponseDto getSelectedPost(Long PostId) {
        Post post = findPostById(PostId);
        return new PostResponseDto(post);
    }

//    4. 선택 게시글 수정 메서드
    @Transactional
    public PostResponseDto update(Long PostId, PostRequestDto postRequestDto, User user) {
        Post post = findPostById(PostId);
//        user가 ADMIN이면 모든 게시글 수정 가능. 아니면 작성자 검증
        if(user.getRole().equals(UserRoleEnum.ADMIN)) {
            post.update(postRequestDto);
            return new PostResponseDto(post);
        }
        if (!user.getId().equals(post.getUser().getId())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        post.update(postRequestDto);
        return new PostResponseDto(post);
    }

    // 5. 선택 게시글 삭제 메서드
    @Transactional
    public MessageResponseDto deletePost(Long PostId, User user) {
        Post post = findPostById(PostId);
//        user가 ADMIN이면 모든 게시글 삭제 가능. 아니면 작성자 검증.
        if(user.getRole().equals(UserRoleEnum.ADMIN)){
//        게시글에 달린 댓글도 다 삭제
            commentRepository.deleteAllByPost(post);
            postRepository.deleteById(PostId);
            return new MessageResponseDto("게시글을 삭제했습니다.", HttpStatus.OK);
        }

        if (!post.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
//        게시글에 달린 댓글도 다 삭제
        commentRepository.deleteAllByPost(post);
        postRepository.deleteById(PostId);
        return new MessageResponseDto("게시글을 삭제했습니다.", HttpStatus.OK);
    }

}
