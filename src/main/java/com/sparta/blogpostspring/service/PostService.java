package com.sparta.blogpostspring.service;

import com.sparta.blogpostspring.dto.PostRequestDto;
import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

//  전체게시글 조회 메서드
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

//    게시글 작성 메서드
    public Post createpost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }
}
