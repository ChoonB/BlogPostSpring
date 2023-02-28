package com.sparta.blogpostspring.service;

import com.sparta.blogpostspring.dto.PostRequestDto;
import com.sparta.blogpostspring.dto.PostResponseDto;
import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

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
    public Post createpost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }

    @Transactional(readOnly = true)
    public PostResponseDto getSelectedPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new PostResponseDto(post);

    }

    public PostResponseDto update(Long id, PostRequestDto postRequestDto) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(!postRequestDto.getPassword().equals(post.getPassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        post.update(postRequestDto);
        return new PostResponseDto(post);

    }

    public Boolean deletePost(Long id, String password) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(!password.equals(post.getPassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        postRepository.deleteById(id);
        return true;

    }
}
