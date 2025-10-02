package com.practice.graphql.domain.posting

import com.practice.graphql.domain.member.Member
import com.practice.graphql.domain.posting.presentation.dto.request.PostingUpdateReq
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Posting(
    var title: String,
    var content: String,
    @ManyToOne
    @JoinColumn(name = "writer")
    val writer: Member,
){
    @Id
    @GeneratedValue
    val id: Long = 0

    fun update(postingUpdateReq: PostingUpdateReq){
        this.title = postingUpdateReq.title
        this.content = postingUpdateReq.content
    }
}