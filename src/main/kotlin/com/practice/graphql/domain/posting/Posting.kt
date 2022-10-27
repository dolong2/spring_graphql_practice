package com.practice.graphql.domain.posting

import com.practice.graphql.domain.member.Member
import com.practice.graphql.domain.posting.presentation.dto.request.PostingUpdateReq
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Posting(
    var title: String,
    var content: String,
    @ManyToOne
    @JoinColumn(name = "writer")
    val writer: Member,
){
    @Id @GeneratedValue
    val id: Long = 0

    fun update(postingUpdateReq: PostingUpdateReq){
        this.title = postingUpdateReq.title
        this.content = postingUpdateReq.content
    }
}