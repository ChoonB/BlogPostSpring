package com.sparta.blogpostspring.repository;

import com.sparta.blogpostspring.entity.Comment;
import com.sparta.blogpostspring.entity.Heart;
import com.sparta.blogpostspring.entity.Post;
import com.sparta.blogpostspring.entity.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    List<Heart> findAllByPost(Post post);
    List<Heart> findAllByComment(Comment comment);
    List<Heart> findAllBySubComment(SubComment subComment);
    void deleteAllByComment(Comment comment);
    void deleteAllByPost(Post post);
    void deleteAllBySubComment(SubComment subComment);
}
