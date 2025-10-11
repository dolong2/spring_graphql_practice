package com.practice.graphql.domain.posting.presentation.dto.request

import com.practice.graphql.domain.member.Member
import com.practice.graphql.domain.posting.Posting
import com.practice.graphql.domain.topic.Topic

class PostingReq(
    val title:String,
    val content: String,
) {
    fun toEntity(member: Member, topic: Topic) =
        Posting(
            title = title,
            content = content,
            writer = member,
            topic = topic
        )
}