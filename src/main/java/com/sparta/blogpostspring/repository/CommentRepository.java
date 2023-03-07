package com.sparta.blogpostspring.repository;

import com.sparta.blogpostspring.entity.Comment;
import com.sparta.blogpostspring.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostOrderByCreatedAtDesc(Post post);
}
