package com.practice.graphql.domain.posting.presentation.dto.request

import com.practice.graphql.domain.member.Member
import com.practice.graphql.domain.posting.Posting

class PostingUpdateReq(
    val title:String,
    val content: String,
){
    fun toEntity(member: Member) =
        Posting(
            title = title,
            content = content,
            writer = member,
        )
}