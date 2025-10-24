package com.practice.graphql.domain.comment.presentation.dto.request

import com.practice.graphql.domain.comment.Comment
import com.practice.graphql.domain.member.Member
import com.practice.graphql.domain.posting.Posting

data class WriteCommentReqDto(
    val content: String
) {
    fun toEntity(posting: Posting, member: Member): Comment =
        Comment(
            content = content,
            posting = posting,
            writer = member
        )
}
