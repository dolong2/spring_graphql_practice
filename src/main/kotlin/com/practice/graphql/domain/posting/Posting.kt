package com.practice.graphql.domain.posting

import com.practice.graphql.domain.comment.Comment
import com.practice.graphql.domain.member.Member
import com.practice.graphql.domain.posting.presentation.dto.request.PostingUpdateReq
import com.practice.graphql.domain.topic.Topic
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Posting(
    var title: String,
    var content: String,
    @ManyToOne
    @JoinColumn(name = "writer_id")
    val writer: Member,
    @ManyToOne
    @JoinColumn(name = "topic_id")
    val topic: Topic,
    @OneToMany
    val comments: MutableList<Comment> = mutableListOf(),
){
    @Id
    @GeneratedValue
    val id: Long = 0

    fun update(postingUpdateReq: PostingUpdateReq){
        this.title = postingUpdateReq.title
        this.content = postingUpdateReq.content
    }
}