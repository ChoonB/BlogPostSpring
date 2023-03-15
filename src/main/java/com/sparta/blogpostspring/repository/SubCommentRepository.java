package com.sparta.blogpostspring.repository;

import com.sparta.blogpostspring.entity.Comment;
import com.sparta.blogpostspring.entity.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCommentRepository extends JpaRepository<SubComment, Long> {
    List<SubComment> findAllByComment(Comment comment);
    void deleteAllByComment(Comment comment);
}
