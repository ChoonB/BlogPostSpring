package com.sparta.blogpostspring.service;

import com.sparta.blogpostspring.dto.PostRequestDto;
import com.sparta.blogpostspring.dto.PostResponseDto;
import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.entity.User;
import com.sparta.blogpostspring.jwt.JwtUtil;
import com.sparta.blogpostspring.repository.PostRepository;
import com.sparta.blogpostspring.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    //    토큰 검증 그냥 메서드로 만듬. 여기에도 @Transactional 해야하나?
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

//  전체게시글 조회 메서드
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<PostResponseDto> prList = new ArrayList<>();
        List<Post> allByOrderByCreatedAtDesc = postRepository.findAllByOrderByCreatedAtDesc();
        for (Post post : allByOrderByCreatedAtDesc) {
            prList.add(new PostResponseDto(post));
        }
        return prList;
    }


//    게시글 작성 메서드
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, HttpServletRequest request) {
        // 토큰 검증 메서드
        User user = findUserByToken(request);
        Post post = new Post(postRequestDto, user);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

//    선택 게시글 조회 메서드
    @Transactional(readOnly = true)
    public PostResponseDto getSelectedPost(Long id) {
        Post post = findPostById(id);
        return new PostResponseDto(post);
    }

//    선택 게시글 수정
    @Transactional
    public PostResponseDto update(Long id, PostRequestDto postRequestDto) throws Exception {
        Post post = findPostById(id);
        if(!postRequestDto.getPassword().equals(post.getPassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        post.update(postRequestDto);
        return new PostResponseDto(post);
    }

//    @Transactional
//    public boolean deletePost(Long id, String password) throws Exception {
//        Post post = findPostById(id);
//        if(!password.equals(post.getPassword())){
//            throw new Exception("비밀번호가 일치하지 않습니다.");
//        }
//        postRepository.deleteById(id);
//
//        return true;
//
//    }

    @Transactional
    public boolean deletePost2(Long id, String password) throws Exception {
        Post post = findPostById(id);
        if(!password.equals(post.getPassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        postRepository.deleteById(id);
        return true;
    }

    private Post findPostById(Long id){
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }


}
