package com.practice.graphql.domain.comment.repository

import com.practice.graphql.domain.comment.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
}