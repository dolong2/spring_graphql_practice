package com.practice.graphql.domain.posting.presentation.dto.response

import com.practice.graphql.domain.comment.presentation.dto.response.CommentListRes
import com.practice.graphql.domain.comment.presentation.dto.response.CommentRes
import com.practice.graphql.domain.posting.Posting

class PostingRes(
    val id: Long,
    val title: String,
    val content: String,
    val writer: Long,
    val commentList: CommentListRes
){
    constructor(posting: Posting) : this(
        id = posting.id,
        title = posting.title,
        content = posting.content,
        writer = posting.writer.id,
        commentList = CommentListRes(posting.comments.map { CommentRes(it) })
    )
}