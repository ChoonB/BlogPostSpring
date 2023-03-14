package com.sparta.blogpostspring.repository;

import com.sparta.blogpostspring.entity.Comment;
import com.sparta.blogpostspring.entity.Heart;
import com.sparta.blogpostspring.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    List<Heart> findAllByPost(Post post);
    List<Heart> findAllByComment(Comment comment);
    void deleteAllByComment(Comment comment);
    void deleteAllByPost(Post post);
}
