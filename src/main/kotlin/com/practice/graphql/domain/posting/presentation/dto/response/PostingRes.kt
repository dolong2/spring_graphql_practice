package com.practice.graphql.domain.posting.presentation.dto.response

import com.practice.graphql.domain.posting.Posting

class PostingRes(
    val id: Long,
    val title: String,
    val content: String,
    val writer: Long,
){
    constructor(posting: Posting) : this(
        id = posting.id,
        title = posting.title,
        content = posting.content,
        writer = posting.writer.id
    )
}