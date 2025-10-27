package com.practice.graphql.domain.comment.presentation.dto.response

import com.practice.graphql.domain.comment.Comment
import java.time.LocalDateTime

data class CommentRes(
    val id: Long,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
    val writer: Long
) {
    constructor(comment: Comment) : this(
        id = comment.id,
        content = comment.content,
        createdAt = comment.createdAt,
        updatedAt = comment.updatedAt,
        writer = comment.writer.id
    )
}
