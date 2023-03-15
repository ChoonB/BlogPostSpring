package com.sparta.blogpostspring.repository;

import com.sparta.blogpostspring.entity.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCommentRepository extends JpaRepository<SubComment, Long> {
}
