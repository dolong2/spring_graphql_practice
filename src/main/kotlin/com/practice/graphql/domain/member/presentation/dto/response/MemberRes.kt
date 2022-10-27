package com.practice.graphql.domain.member.presentation.dto.response

import com.practice.graphql.domain.member.Member
import com.practice.graphql.domain.posting.presentation.dto.response.PostingRes

class MemberRes(
    val name: String,
    val postings: List<PostingRes>
) {
    constructor(member: Member):this(
        name = member.name,
        postings = member.postings.map { PostingRes(it) }
    )
}