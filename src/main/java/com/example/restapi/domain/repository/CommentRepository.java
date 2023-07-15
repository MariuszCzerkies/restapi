package com.example.restapi.domain.repository;

import com.example.restapi.external.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query
    List<Comment> findAllByPostIdIn(List<Long> ids);
}
