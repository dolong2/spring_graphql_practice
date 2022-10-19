package com.practice.graphql.domain.posting.presentation.dto.request

import com.practice.graphql.domain.posting.Posting

class PostingReq(
    val title:String,
    val content: String,
) {
    fun toEntity() =
        Posting(
            title = title,
            content = content,
        )
}