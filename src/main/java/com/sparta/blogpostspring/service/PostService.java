package com.sparta.blogpostspring.service;

import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}
