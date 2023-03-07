package com.sparta.blogpostspring.repository;

import com.sparta.blogpostspring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
